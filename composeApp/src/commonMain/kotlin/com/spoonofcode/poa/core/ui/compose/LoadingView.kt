package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spoonofcode.poa.core.ui.Paddings
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.poa
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun LoadingView(
    iconRes: DrawableResource = Res.drawable.poa,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(Paddings.screenPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PulsatingIcon(iconRes = iconRes)
    }
}
