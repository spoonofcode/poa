package com.spoonofcode.core.presentation.theme


import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.roboto_bold
import com.spoonofcode.core.presentation.roboto_extra_bold
import com.spoonofcode.core.presentation.roboto_extra_light
import com.spoonofcode.core.presentation.roboto_light
import com.spoonofcode.core.presentation.roboto_medium
import com.spoonofcode.core.presentation.roboto_regular
import com.spoonofcode.core.presentation.roboto_semi_bold

@Composable
fun appFontFamily() = FontFamily(
    Font(resource = Res.font.roboto_extra_light, weight = FontWeight.ExtraLight),
    Font(resource = Res.font.roboto_light, weight = FontWeight.Light),
    Font(resource = Res.font.roboto_regular, weight = FontWeight.Normal),
    Font(resource = Res.font.roboto_medium, weight = FontWeight.Medium),
    Font(resource = Res.font.roboto_semi_bold, weight = FontWeight.SemiBold),
    Font(resource = Res.font.roboto_bold, weight = FontWeight.Bold),
    Font(resource = Res.font.roboto_extra_bold, weight = FontWeight.ExtraBold),
)

// Default Material 3 typography values-pl-en
val baseline = Typography()

/*
Material 3 Typography mapping to Material 2 (H1–H6):

displayLarge   -> H1
displayMedium  -> H2
displaySmall   -> H3
headlineLarge  -> H4
headlineMedium -> H5
headlineSmall  -> H6
*/

@Composable
fun appTypography(): Typography {
    val fontFamily = appFontFamily()
    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = fontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = fontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = fontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = fontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = fontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = fontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = fontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = fontFamily),
    )
}

