package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CircleImage(
    modifier: Modifier = Modifier,
    imageRes: DrawableResource,
    imageSize: Dp = 120.dp,
) {
    Surface(
        modifier = modifier
            .size(imageSize),
        shape = CircleShape,
    ) {
        Image(
            painter = painterResource(resource = imageRes),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

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