package com.spoonofcode.poa.feature.profile.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.feature.profile.domain.model.Profile

@Immutable
internal data class ProfileEditViewState(
    val profile: Profile? = null,
) : BaseViewState()