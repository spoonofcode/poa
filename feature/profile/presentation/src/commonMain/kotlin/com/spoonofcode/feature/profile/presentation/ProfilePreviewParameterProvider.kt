package com.spoonofcode.feature.profile.presentation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spoonofcode.feature.profile.domain.model.Profile
import com.spoonofcode.feature.profile.presentation.PreviewParameterData.profile

/**
 * This [PreviewParameterProvider](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/PreviewParameterProvider)
 * provides list of [Profile] for Composable previews.
 */
class ProfilePreviewParameterProvider : PreviewParameterProvider<Profile> {

    override val values: Sequence<Profile> = sequenceOf(profile)
}

object PreviewParameterData {
    val profile = Profile(
        name = "Bartosz luczak (Lycha)",
        numberOfProductsOwnedByUser = 2,
        customLink = "https://example.com"
    )
}