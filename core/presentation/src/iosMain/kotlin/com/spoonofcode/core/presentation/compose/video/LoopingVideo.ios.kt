package com.spoonofcode.core.presentation.compose.video

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerActionAtItemEndNone
import platform.AVFoundation.AVPlayerItemDidPlayToEndTimeNotification
import platform.AVFoundation.actionAtItemEnd
import platform.AVFoundation.currentItem
import platform.AVFoundation.muted
import platform.AVFoundation.play
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSURL

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun LoopingVideo(
    url: String,
    modifier: Modifier,
    autoPlay: Boolean,
    mute: Boolean
) = UIKitView(
    modifier = modifier,
    factory = {
        val player = AVPlayer(NSURL(string = url))
        player.actionAtItemEnd = AVPlayerActionAtItemEndNone
        player.muted = mute

        // restart when the clip ends
        NSNotificationCenter.defaultCenter.addObserverForName(
            name = AVPlayerItemDidPlayToEndTimeNotification,
            `object` = player.currentItem,
            queue = null
        ) { _ ->
            // TODO Fix player for iOS
//            player.seekToTime(kCMTimeZero)
        }

        val controller = AVPlayerViewController().apply {
            this.player = player
            showsPlaybackControls = false
        }
        if (autoPlay) player.play()
        controller.view
    }
)