package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Google
import compose.icons.fontawesomeicons.brands.Instagram
import compose.icons.fontawesomeicons.brands.Linkedin
import compose.icons.fontawesomeicons.brands.Twitter

@Composable
fun SocialIconsRow(
    iconSize: Dp = 32.dp,
    iconSpacing: Dp = 8.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialIcon(FontAwesomeIcons.Brands.Facebook, iconSize)
        SocialIcon(FontAwesomeIcons.Brands.Twitter, iconSize)
        SocialIcon(FontAwesomeIcons.Brands.Google, iconSize)
        SocialIcon(FontAwesomeIcons.Brands.Instagram, iconSize)
        SocialIcon(FontAwesomeIcons.Brands.Linkedin, iconSize)
    }
}

@Composable
private fun SocialIcon(
    icon: ImageVector,
    size: Dp
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier
            .size(size)
    )
}