package com.spoonofcode.poa.feature.partner.edit

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class PartnerEditViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
) : BaseViewState()