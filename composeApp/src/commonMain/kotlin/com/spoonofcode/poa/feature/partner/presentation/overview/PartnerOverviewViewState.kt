package com.spoonofcode.poa.feature.partner.presentation.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.core.model.PartnerCategory
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class PartnerOverviewViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val searchText: String = "",
    val partnerCategories: List<PartnerCategory> = emptyList(),
    val selectedPartnerCategories: Set<String> = emptySet(),
    val initPartners: List<Partner> = emptyList(),
    val filteredPartners: List<Partner> = emptyList(),
) : BaseViewState(screenState)