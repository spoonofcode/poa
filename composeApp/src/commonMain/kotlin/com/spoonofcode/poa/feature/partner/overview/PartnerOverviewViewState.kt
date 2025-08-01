package com.spoonofcode.poa.feature.partner.overview

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class PartnerOverviewViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
) : BaseViewState()