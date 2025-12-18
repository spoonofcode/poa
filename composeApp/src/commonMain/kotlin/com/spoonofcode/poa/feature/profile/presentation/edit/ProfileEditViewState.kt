package com.spoonofcode.poa.feature.profile.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class ProfileEditViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val profile: Profile? = null,
) : BaseViewState(screenState)