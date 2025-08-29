package com.spoonofcode.poa.feature.appsettings

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class AppSettingsViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
) : BaseViewState(screenState)