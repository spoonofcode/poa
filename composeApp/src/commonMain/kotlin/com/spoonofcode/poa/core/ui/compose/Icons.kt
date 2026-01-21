package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Icons {
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
}