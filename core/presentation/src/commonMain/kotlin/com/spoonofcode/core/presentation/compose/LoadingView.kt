package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.taggy_go_logo

@Composable
fun LoadingView(
    iconRes: DrawableResource = Res.drawable.taggy_go_logo,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(Paddings.screenPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomIcons.PulsatingIcon(iconRes = iconRes)
    }
}
