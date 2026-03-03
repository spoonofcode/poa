package com.spoonofcode.core.presentation.compose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

object CustomIcons {
    @Composable
    fun CircleIcon(
        modifier: Modifier = Modifier,
        imageVector: ImageVector,
        backgroundColor: Color = MaterialTheme.colorScheme.background,
        iconTint: Color = MaterialTheme.colorScheme.onSurface,
        imageSize: Dp = 120.dp,
    ) {
        Surface(
            modifier = modifier.size(imageSize),
            shape = CircleShape,
            color = backgroundColor,
            tonalElevation = 1.dp,
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(imageSize * 0.5f)
            )
        }
    }

    @Composable
    fun PulsatingIcon(
        iconRes: DrawableResource,
        sizeDp: Dp = 160.dp,
        duration: Int = 650
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "pulseTransition")

        val scale by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.15f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = duration, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "scaleAnim"
        )

        val alpha by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 0.6f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = duration, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "alphaAnim"
        )

        Surface(
            modifier = Modifier
                .size(sizeDp),
            shape = CircleShape,
        ) {
            Image(
                painter = painterResource(resource = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(sizeDp)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .alpha(alpha),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background)
            )
        }
    }

    @Composable
    fun IconWithBadge(
        icon: ImageVector,
        contentDescription: String? = null,
        count: Int,
        onClick: () -> Unit
    ) {
        BadgedBox(
            badge = {
                if (count > 0) {
                    Badge {
                        Text(if (count > 99) "99+" else count.toString())
                    }
                }
            },
            modifier = Modifier.padding(end = 12.dp)
        ) {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription
                )
            }
        }
    }
}