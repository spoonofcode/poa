package com.spoonofcode.core.presentation.compose.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun VideoAnimation(videoLink: String) {
    Box {
        LoopingVideo(
            url = videoLink,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(
                    ratio = 1f,
                    matchHeightConstraintsFirst = true
                )
                .clip(RoundedCornerShape(16.dp))
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            mute = true,
        )
    }
}