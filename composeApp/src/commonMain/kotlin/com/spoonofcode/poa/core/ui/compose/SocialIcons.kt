package com.spoonofcode.poa.core.ui.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Web
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
import com.spoonofcode.poa.core.network.getUrlOpener
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Instagram
import compose.icons.fontawesomeicons.brands.Twitter
import compose.icons.fontawesomeicons.brands.Weebly
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
    // layout controls
    iconSize: Dp = 30.dp,
    containerSize: Dp = 54.dp,
    horizontalPadding: Dp = 16.dp,
    spacing: Dp = 12.dp,
    shape: Shape = CircleShape,
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
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            SocialIcon(
                icon = item.icon(),
                url = item.url,
                size = containerSize,
                iconSize = iconSize,
                colors = item.brandColors(),
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

@Composable
private fun SocialItem.brandColors(): SocialColors {
    val themeBg = MaterialTheme.colorScheme.surfaceVariant
    val themeFg = MaterialTheme.colorScheme.onSurfaceVariant
    // gentle brand-leaning tints (kept subtle to work with light/dark)
    return when (this) {
        is SocialItem.Web -> SocialColors(themeBg, themeFg)
        is SocialItem.Instagram -> SocialColors(themeBg.copy(alpha = 0.9f), Color(0xFFE1306C))
        is SocialItem.Facebook -> SocialColors(themeBg.copy(alpha = 0.9f), Color(0xFF1877F2))
        is SocialItem.YouTube -> SocialColors(themeBg.copy(alpha = 0.9f), Color(0xFFFF0000))
        is SocialItem.X -> SocialColors(themeBg.copy(alpha = 0.9f), Color(0xFF000000))
    }
}

private data class SocialColors(val container: Color, val content: Color)

/**
 * Polished icon button with:
 * - min touch target (48dp default)
 * - ripple + pressed scale
 * - keyboard focusable, role=Button
 */
@Composable
private fun SocialIcon(
    icon: ImageVector,
    url: String,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,            // container size (touch target)
    iconSize: Dp = 24.dp,        // actual icon size
    colors: SocialColors = SocialColors(
        MaterialTheme.colorScheme.surfaceVariant,
        MaterialTheme.colorScheme.onSurfaceVariant
    ),
    shape: Shape = CircleShape,
) {
    val urlOpener = remember { getUrlOpener() }
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.94f else 1f,
        label = "pressScale"
    )

    Surface(
        modifier = modifier
            .size(size)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(shape)
            .indication(interaction, LocalIndication.current)
            .clickable(
                interactionSource = interaction,
                indication = LocalIndication.current,
                role = Role.Button
            ) { urlOpener.openUrl(url) }
            .semantics { contentDescription = url }, // screen readers will announce link target
        shape = shape,
        color = colors.container,
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.content,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}