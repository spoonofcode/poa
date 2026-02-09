package com.spoonofcode.feature.profile.presentation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spoonofcode.feature.profile.domain.model.Profile
import com.spoonofcode.feature.profile.presentation.PreviewParameterData.profile_1
import com.spoonofcode.feature.profile.presentation.PreviewParameterData.profile_2

/**
 * This [PreviewParameterProvider](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/PreviewParameterProvider)
 * provides list of [Profile] for Composable previews.
 */
class ProfilePreviewParameterProvider : PreviewParameterProvider<Profile> {
    override val values: Sequence<Profile> = sequenceOf(profile_1, profile_2)
}

object PreviewParameterData {
    val profile_1 =
        Profile(
            name = "Bartosz luczak (Lycha)",
            numberOfProductsOwnedByUser = 4,
            customLink = "https://example.com"
        )

    val profile_2 = Profile(
        name = "Bartosz luczak (Lycha)",
        numberOfProductsOwnedByUser = 2,
        customLink = "https://example.com"
    )
}