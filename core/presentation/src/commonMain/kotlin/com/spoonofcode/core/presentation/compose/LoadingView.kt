package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import poa.core.presentation.generated.resources.Res
import poa.core.presentation.generated.resources.poa

@Composable
fun LoadingView(
    iconRes: DrawableResource = Res.drawable.poa,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(Paddings.screenPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomIcons.PulsatingIcon(iconRes = iconRes)
    }
}
