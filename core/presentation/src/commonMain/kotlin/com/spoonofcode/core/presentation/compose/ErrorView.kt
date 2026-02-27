package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.data_could_not_be_loaded
import com.spoonofcode.core.presentation.reload
import com.spoonofcode.core.presentation.taggy_go_logo

@Composable
fun ErrorView(
    iconRes: DrawableResource = Res.drawable.taggy_go_logo,
    sizeDp: Dp = 160.dp,
    reload: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(Paddings.screenPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            modifier = Modifier
                .size(160.dp),
            shape = CircleShape,
        ) {
            Image(
                painter = painterResource(resource = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(sizeDp)
                    .background(MaterialTheme.colorScheme.onBackground),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background)
            )
        }
        Spacers.VerticalBetweenFields()

        Texts.BLB(stringResource(Res.string.data_could_not_be_loaded))

        Spacers.VerticalBetweenFields()

        Buttons.SecondaryButton(
            text = stringResource(resource = Res.string.reload),
            onClick = reload
        )
    }
}
