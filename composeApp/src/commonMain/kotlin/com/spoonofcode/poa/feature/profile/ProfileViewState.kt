package com.spoonofcode.poa.feature.profile

import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class ProfileViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val profile: Profile? = null,
) : BaseViewState()