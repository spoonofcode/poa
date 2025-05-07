package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.Paddings
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.data_could_not_be_loaded
import com.spoonofcode.poa.resources.dojo_pro
import com.spoonofcode.poa.resources.reload
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorView(
    iconRes: DrawableResource = Res.drawable.dojo_pro,
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
                    .size(sizeDp),
                contentScale = ContentScale.Crop
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
