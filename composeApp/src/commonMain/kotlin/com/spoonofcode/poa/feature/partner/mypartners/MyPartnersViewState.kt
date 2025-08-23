package com.spoonofcode.poa.feature.partner.mypartners

import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewState

data class MyPartnersViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val partners: List<Partner> = emptyList()
) : BaseViewState()