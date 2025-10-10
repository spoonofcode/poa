package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.network.getUrlOpener
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Instagram
import compose.icons.fontawesomeicons.brands.Twitter
import compose.icons.fontawesomeicons.brands.Youtube

@Composable
fun SocialIcons(
    websiteUrl: String? = null,
    instagramUrl: String? = null,
    facebookUrl: String? = null,
    youtubeUrl: String? = null,
    xUrl: String? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialIcon(Icons.Default.Web, url = websiteUrl)
        SocialIcon(FontAwesomeIcons.Brands.Instagram, url = instagramUrl)
        SocialIcon(FontAwesomeIcons.Brands.Facebook, url = facebookUrl)
        SocialIcon(FontAwesomeIcons.Brands.Youtube, url = youtubeUrl)
        SocialIcon(FontAwesomeIcons.Brands.Twitter, url = xUrl)
    }
}

@Composable
fun SocialIcon(
    icon: ImageVector,
    url: String?,
    modifier: Modifier = Modifier,
    size: Dp = 32.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    shape: Shape = CircleShape,
    elevation: Dp = 3.dp
) {
    val urlOpener = remember { getUrlOpener() }

    Surface(
        modifier = modifier
            .size(size * 1.8f)
            .clickable(enabled = url != null) {
                url?.let { urlOpener.openUrl(it) }
            },
        shape = shape,
        color = backgroundColor,
        tonalElevation = elevation,
        shadowElevation = elevation
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .aspectRatio(1f)
        )
    }
}