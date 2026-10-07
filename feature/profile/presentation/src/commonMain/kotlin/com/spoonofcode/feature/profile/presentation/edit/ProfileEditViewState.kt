package com.spoonofcode.feature.profile.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.domain.ext.isValidEmail
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.profile.domain.model.Profile

@Immutable
internal data class ProfileEditViewState(
    val profile: Profile,
) : BaseViewState() {
    val isSaveChangesButtonEnable: Boolean
        get() = profile.email.isValidEmail()
}