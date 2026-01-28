package com.spoonofcode.core.presentation.compose.video

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.PlayerSurface

@Composable
actual fun LoopingVideo(
    url: String,
    modifier: Modifier,
    autoPlay: Boolean,
    mute: Boolean
) {
    val ctx = LocalContext.current
    val player = remember { buildPlayer(ctx, url, autoPlay, mute) }

    DisposableEffect(Unit) { onDispose { player.release() } }

    PlayerSurface(player = player, modifier = modifier)
}

private fun buildPlayer(
    ctx: Context,
    url: String,
    autoPlay: Boolean,
    mute: Boolean
) = ExoPlayer.Builder(ctx).build().apply {
    setMediaItem(MediaItem.fromUri(url))
    repeatMode = Player.REPEAT_MODE_ALL
    volume = if (mute) 0f else 1f
    prepare()
    playWhenReady = autoPlay
}