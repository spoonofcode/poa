package com.spoonofcode.poa.feature.profile.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.core.ui.base.BaseViewState

@Immutable
internal data class ProfileDetailsViewState(
    val profile: Profile? = null,
) : BaseViewState()