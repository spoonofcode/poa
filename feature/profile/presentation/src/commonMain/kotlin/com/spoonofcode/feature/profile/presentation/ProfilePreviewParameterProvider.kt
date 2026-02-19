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
            id = "1",
            email = "exmaple.mail.1@gmail.com",
            roles = listOf("user"),
        )

    val profile_2 = Profile(
        id = "2",
        email = "exmaple.mail.2@gmail.com",
        roles = listOf("user","partner"),
    )
}