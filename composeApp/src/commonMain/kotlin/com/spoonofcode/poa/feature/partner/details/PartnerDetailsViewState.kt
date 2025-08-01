package com.spoonofcode.poa.feature.partner.details

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class PartnerDetailsViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
) : BaseViewState()