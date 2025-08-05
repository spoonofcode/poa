package com.spoonofcode.poa.feature.partner.panel

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class PartnerPanelViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
) : BaseViewState()