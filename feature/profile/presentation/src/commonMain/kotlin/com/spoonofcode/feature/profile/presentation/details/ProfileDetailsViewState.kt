package com.spoonofcode.feature.profile.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.feature.profile.domain.model.Profile
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class ProfileDetailsViewState(
    val profile: Profile? = null,
) : BaseViewState()