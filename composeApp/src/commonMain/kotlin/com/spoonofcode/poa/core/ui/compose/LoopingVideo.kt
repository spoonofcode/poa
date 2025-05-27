package com.spoonofcode.poa.core.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun LoopingVideo(
    url: String,
    modifier: Modifier = Modifier,
    autoPlay: Boolean = true,
    mute: Boolean = true
)