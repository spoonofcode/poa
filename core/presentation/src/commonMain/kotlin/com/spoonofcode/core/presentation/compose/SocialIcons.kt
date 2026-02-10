package com.spoonofcode.core.presentation.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.web.getUrlOpener
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Instagram
import compose.icons.fontawesomeicons.brands.Twitter
import compose.icons.fontawesomeicons.brands.Wordpress
import compose.icons.fontawesomeicons.brands.Youtube

@Composable
fun SocialIcons(
    websiteUrl: String? = null,
    instagramUrl: String? = null,
    facebookUrl: String? = null,
    youtubeUrl: String? = null,
    xUrl: String? = null,
    modifier: Modifier = Modifier,
    iconSize: Dp = 30.dp,
    containerSize: Dp = 54.dp,
    spacing: Dp = 16.dp,
    shape: Shape = MaterialTheme.shapes.medium, // More modern than perfect circle
) {
    val items = buildList {
        if (!websiteUrl.isNullOrBlank()) add(SocialItem.Web(websiteUrl))
        if (!instagramUrl.isNullOrBlank()) add(SocialItem.Instagram(instagramUrl))
        if (!facebookUrl.isNullOrBlank()) add(SocialItem.Facebook(facebookUrl))
        if (!youtubeUrl.isNullOrBlank()) add(SocialItem.YouTube(youtubeUrl))
        if (!xUrl.isNullOrBlank()) add(SocialItem.X(xUrl))
    }
    if (items.isEmpty()) return

    Row(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            SocialIcon(
                icon = item.icon(),
                url = item.url,
                size = containerSize,
                iconSize = iconSize,
                brandColor = item.brandColor(),
                label = item.label,
                shape = shape,
            )
        }
    }
}

private sealed class SocialItem(val url: String, val label: String) {
    class Web(url: String) : SocialItem(url, "Website")
    class Instagram(url: String) : SocialItem(url, "Instagram")
    class Facebook(url: String) : SocialItem(url, "Facebook")
    class YouTube(url: String) : SocialItem(url, "YouTube")
    class X(url: String) : SocialItem(url, "X (Twitter)")
}

@Composable
private fun SocialItem.icon(): ImageVector = when (this) {
    is SocialItem.Web -> FontAwesomeIcons.Brands.Wordpress
    is SocialItem.Instagram -> FontAwesomeIcons.Brands.Instagram
    is SocialItem.Facebook -> FontAwesomeIcons.Brands.Facebook
    is SocialItem.YouTube -> FontAwesomeIcons.Brands.Youtube
    is SocialItem.X -> FontAwesomeIcons.Brands.Twitter
}

private fun SocialItem.brandColor(): Color = when (this) {
    is SocialItem.Web -> Color(0xFF21759B)
    is SocialItem.Instagram -> Color(0xFFE1306C)
    is SocialItem.Facebook -> Color(0xFF1877F2)
    is SocialItem.YouTube -> Color(0xFFFF0000)
    is SocialItem.X -> Color(0xFF000000)
}

@Composable
private fun SocialIcon(
    icon: ImageVector,
    url: String,
    brandColor: Color,
    label: String,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    iconSize: Dp = 24.dp,
    shape: Shape = CircleShape,
) {
    val urlOpener = remember { getUrlOpener() }
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.92f else 1f,
        label = "pressScale"
    )

    val containerColor by animateColorAsState(
        targetValue = if (pressed) brandColor.copy(alpha = 0.25f) else brandColor.copy(alpha = 0.12f),
        label = "containerColor"
    )

    Surface(
        modifier = modifier
            .size(size)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(shape)
            .clickable(
                interactionSource = interaction,
                indication = null, // Custom scale animation used instead
                role = Role.Button
            ) { urlOpener.openUrl(url) }
            .semantics { contentDescription = label },
        shape = shape,
        color = containerColor,
        border = BorderStroke(
            width = 1.dp,
            color = brandColor.copy(alpha = 0.2f)
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = brandColor,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}
