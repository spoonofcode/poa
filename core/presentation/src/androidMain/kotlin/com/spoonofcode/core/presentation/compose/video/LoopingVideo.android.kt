package com.spoonofcode.core.presentation.compose.video

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.compose.PlayerSurface

@OptIn(UnstableApi::class)
@Composable
actual fun LoopingVideo(
    url: String,
    modifier: Modifier,
    autoPlay: Boolean,
    mute: Boolean,
    onLoadingChanged: (Boolean) -> Unit,
    onError: (Exception) -> Unit,
) {
    val ctx = LocalContext.current
    val player = remember { buildPlayer(ctx, url, autoPlay, mute, onLoadingChanged, onError) }

    DisposableEffect(Unit) { onDispose { player.release() } }

    PlayerSurface(player = player, modifier = modifier)
}

@OptIn(UnstableApi::class)
private fun buildPlayer(
    ctx: Context,
    url: String,
    autoPlay: Boolean,
    mute: Boolean,
    onLoadingChanged: (Boolean) -> Unit,
    onError: (Exception) -> Unit,
): ExoPlayer {
    val httpDataSourceFactory =
        DefaultHttpDataSource
            .Factory()
            .setUserAgent(Util.getUserAgent(ctx, "POA"))
            .setAllowCrossProtocolRedirects(true)

    val mediaSourceFactory =
        DefaultMediaSourceFactory(ctx)
            .setDataSourceFactory(httpDataSourceFactory)

    return ExoPlayer
        .Builder(ctx)
        .setMediaSourceFactory(mediaSourceFactory)
        .build()
        .apply {
            addListener(
                object : Player.Listener {
                    override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                        onError(error)
                        onLoadingChanged(false)
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        onLoadingChanged(playbackState == Player.STATE_BUFFERING)
                    }
                },
            )
            setMediaItem(MediaItem.fromUri(url))
            repeatMode = Player.REPEAT_MODE_ALL
            volume = if (mute) 0f else 1f
            prepare()
            playWhenReady = autoPlay
        }
}
