package com.spoonofcode.poa.feature.appsettings

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class AppSettingsViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
) : BaseViewState()