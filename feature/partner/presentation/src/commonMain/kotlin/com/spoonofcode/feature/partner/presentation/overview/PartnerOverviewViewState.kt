package com.spoonofcode.feature.partner.presentation.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.model.PartnerCategory

@Immutable
internal data class PartnerOverviewViewState(
    val searchText: String = "",
    val selectedPartnerCategories: Set<String> = emptySet(),
    val partnerCategories: List<PartnerCategory>,
    val initPartners: List<Partner>,
    val filteredPartners: List<Partner>,
) : BaseViewState()