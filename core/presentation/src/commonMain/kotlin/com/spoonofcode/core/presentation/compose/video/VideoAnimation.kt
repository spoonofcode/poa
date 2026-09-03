package com.spoonofcode.core.presentation.compose.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.compose.Images

@Composable
fun VideoAnimation(
    videoLink: String,
    imageLink: String? = null
) {
    var isError by remember { mutableStateOf(false) }

    Box {
        if (!isError) {
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
                onError = {
                    isError = true
                }
            )
        } else {
            imageLink?.let {
                Images.ImageLink(
                    imageLink = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .shadow(4.dp, RoundedCornerShape(16.dp))
                )
            }
        }
    }
}