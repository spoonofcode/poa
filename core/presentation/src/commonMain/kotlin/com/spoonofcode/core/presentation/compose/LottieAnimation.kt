package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import com.spoonofcode.core.presentation.Res.readBytes

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LottieAnimation(
    animationFilePath: String,
    contentDescription: String,
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            readBytes(animationFilePath).decodeToString()
        )
    }
    Image(
        painter = rememberLottiePainter(
            composition = composition,
            iterations = Compottie.IterateForever
        ),
        contentDescription = contentDescription,
    )
}