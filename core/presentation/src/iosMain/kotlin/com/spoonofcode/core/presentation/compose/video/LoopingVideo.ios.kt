package com.spoonofcode.core.presentation.compose.video

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import platform.AVFoundation.*
import platform.AVKit.AVPlayerViewController
import platform.CoreMedia.CMTime
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSURL
import kotlin.time.Duration.Companion.milliseconds

@Composable
actual fun LoopingVideo(
    url: String,
    modifier: Modifier,
    autoPlay: Boolean,
    mute: Boolean,
    onLoadingChanged: (Boolean) -> Unit,
    onError: (Exception) -> Unit
) {
    if (url.isEmpty()) return
    val nsUrl = remember(url) { NSURL.URLWithString(url) } ?: return

    // Używamy klucza, aby przy zmianie URL wszystko zostało stworzone od nowa
    key(nsUrl) {
        LoopingVideoInternal(
            nsUrl = nsUrl,
            modifier = modifier,
            autoPlay = autoPlay,
            mute = mute,
            onLoadingChanged = onLoadingChanged,
            onError = onError
        )
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
private fun LoopingVideoInternal(
    nsUrl: NSURL,
    modifier: Modifier,
    autoPlay: Boolean,
    mute: Boolean,
    onLoadingChanged: (Boolean) -> Unit,
    onError: (Exception) -> Unit
) {
    val player = remember(nsUrl) {
        AVPlayer(uRL = nsUrl).apply {
            actionAtItemEnd = AVPlayerActionAtItemEndNone
        }
    }

    val controller = remember(player) {
        AVPlayerViewController().apply {
            this.player = player
            this.showsPlaybackControls = false
            this.videoGravity = AVLayerVideoGravityResizeAspectFill
        }
    }

    // Monitorowanie ładowania
    LaunchedEffect(player) {
        onLoadingChanged(true)
        while (isActive) {
            val currentItem = player.currentItem
            if (currentItem != null) {
                val status = currentItem.status
                if (status == AVPlayerItemStatusReadyToPlay) {
                    onLoadingChanged(false)
                    if (autoPlay) player.play()
                    break
                } else if (status == AVPlayerItemStatusFailed) {
                    val errorMsg = currentItem.error?.localizedDescription ?: "Load failed"
                    onError(Exception(errorMsg))
                    onLoadingChanged(false)
                    break
                }
            }
            delay(100.milliseconds)
        }
    }

    // Zapętlanie i Cleanup
    DisposableEffect(player) {
        val notificationCenter = NSNotificationCenter.defaultCenter
        val observer = notificationCenter.addObserverForName(
            name = AVPlayerItemDidPlayToEndTimeNotification,
            `object` = player.currentItem,
            queue = NSOperationQueue.mainQueue
        ) { _ ->
            player.seekToTime(
                time = cValue<CMTime> {
                    value = 0
                    timescale = 1
                    flags = 1u
                    epoch = 0
                }
            )
            player.play()
        }

        onDispose {
            notificationCenter.removeObserver(observer)
            player.pause()
            // Krytyczne dla uniknięcia PlayerRemoteXPC err=-12860:
            controller.player = null 
            player.replaceCurrentItemWithPlayerItem(null)
        }
    }

    // Reakcja na zmiany parametrów
    LaunchedEffect(player, mute) { player.muted = mute }
    LaunchedEffect(player, autoPlay) {
        if (autoPlay) player.play() else player.pause()
    }

    UIKitView(
        modifier = modifier,
        factory = { controller.view },
        update = { /* Parametry obsługiwane przez efekty */ }
    )
}
