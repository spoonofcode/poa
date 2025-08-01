package com.spoonofcode.poa.feature.partner.overview

import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class PartnerOverviewViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val searchText: String = "",
    val initPartners: List<Partner> = emptyList(),
    val filteredPartners: List<Partner> = emptyList(),
) : BaseViewState()