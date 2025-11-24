package com.spoonofcode.poa.feature.profile.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class ProfileDetailsViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val profile: Profile? = null,
) : BaseViewState()