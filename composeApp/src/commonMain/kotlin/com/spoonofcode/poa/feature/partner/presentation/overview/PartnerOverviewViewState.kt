package com.spoonofcode.poa.feature.partner.presentation.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory

@Immutable
internal data class PartnerOverviewViewState(
    val searchText: String = "",
    val partnerCategories: List<PartnerCategory> = emptyList(),
    val selectedPartnerCategories: Set<String> = emptySet(),
    val initPartners: List<Partner> = emptyList(),
    val filteredPartners: List<Partner> = emptyList(),
) : BaseViewState()