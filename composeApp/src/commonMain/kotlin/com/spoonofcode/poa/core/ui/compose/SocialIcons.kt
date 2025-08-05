package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    iconSize: Dp = 32.dp,
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
        SocialIcon(Icons.Default.Web, iconSize, websiteUrl)
        SocialIcon(FontAwesomeIcons.Brands.Instagram, iconSize, instagramUrl)
        SocialIcon(FontAwesomeIcons.Brands.Facebook, iconSize, facebookUrl)
        SocialIcon(FontAwesomeIcons.Brands.Youtube, iconSize, youtubeUrl)
        SocialIcon(FontAwesomeIcons.Brands.Twitter, iconSize, xUrl)
    }
}

@Composable
private fun SocialIcon(
    icon: ImageVector,
    size: Dp,
    url: String?,
) {
    val urlOpener = remember { getUrlOpener() }

    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier
            .size(size)
            .clickable {
                if (url != null) {
                    urlOpener.openUrl(url)
                }
            }
    )
}