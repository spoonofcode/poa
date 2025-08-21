package com.spoonofcode.poa.feature.appsettings

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class AppSettingsViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
) : BaseViewState(screenState)