package com.spoonofcode.core.presentation.compose.video

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.delay
import platform.AVFoundation.*
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun LoopingVideo(
    url: String,
    modifier: Modifier,
    autoPlay: Boolean,
    mute: Boolean,
    onLoadingChanged: (Boolean) -> Unit,
    onError: (Exception) -> Unit
) {
    val nsUrl = remember(url) { NSURL.URLWithString(url) }
    if (nsUrl == null) {
        LaunchedEffect(url) { onError(Exception("Invalid URL")) }
        return
    }

    // We store the player and looper together in a remember block.
    // This is CRITICAL: if 'looper' is not held by a strong reference,
    // it will be GCed and the video won't loop.
    val playerPair = remember(nsUrl) {
        val player = AVQueuePlayer()
        val item = AVPlayerItem(uRL = nsUrl)
        val looper = AVPlayerLooper.playerLooperWithPlayer(player, item)
        player to looper
    }

    val player = playerPair.first

    // Track loading status
    LaunchedEffect(player) {
        onLoadingChanged(true)
        // Wait for the first item to be ready
        while (player.currentItem?.status != AVPlayerItemStatusReadyToPlay) {
            if (player.currentItem?.status == AVPlayerItemStatusFailed) {
                onError(Exception(player.currentItem?.error?.localizedDescription ?: "Playback failed"))
                break
            }
            delay(50.milliseconds)
        }
        onLoadingChanged(false)
    }

    // Handle property changes
    LaunchedEffect(mute) {
        player.muted = mute
    }

    LaunchedEffect(autoPlay) {
        if (autoPlay) player.play() else player.pause()
    }

    DisposableEffect(Unit) {
        onDispose {
            player.pause()
            player.removeAllItems()
        }
    }

    UIKitView(
        modifier = modifier,
        factory = {
            val controller = AVPlayerViewController()
            controller.player = player
            controller.showsPlaybackControls = false
            controller.videoGravity = AVLayerVideoGravityResizeAspectFill
            controller.view
        },
        update = {
            // Properties handled by LaunchedEffects
        }
    )
}
