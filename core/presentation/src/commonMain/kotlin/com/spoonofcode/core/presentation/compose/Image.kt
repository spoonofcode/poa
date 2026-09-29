package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CircleImage(
    imageRes: DrawableResource,
    imageSize: Dp = 100.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    Box(
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .size(imageSize * 0.9f),
            shape = CircleShape,
        ) {
            Image(
                painter = painterResource(resource = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onBackground)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background)
            )
        }
    }
}

@Composable
fun ImageLink(
    imageLink: String?,
    modifier: Modifier = Modifier,
    textOnFailure: String? = null,
) {
    if (imageLink.isNullOrBlank()) {
        ImageFallback(
            textOnFailure = textOnFailure,
            modifier = modifier
        )
    } else {
        CoilImage(
            imageModel = { imageLink },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            modifier = modifier.clip(MaterialTheme.shapes.large),
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            failure = {
                ImageFallback(textOnFailure = textOnFailure)
            }
        )
    }
}

@Composable
private fun ImageFallback(
    textOnFailure: String?,
    modifier: Modifier = Modifier,
) {
    val hasText = !textOnFailure.isNullOrEmpty()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                if (hasText) MaterialTheme.colorScheme.onBackground
                else MaterialTheme.colorScheme.surfaceVariant
            ),
        contentAlignment = Alignment.Center
    ) {
        if (hasText) {
            TextTLB(
                text = textOnFailure,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.BrokenImage,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
