package com.spoonofcode.poa.feature.partner.presentation.overview

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface PartnerOverviewViewAction: BaseViewAction {
    data class ChangeSearchText(val searchText: String) : PartnerOverviewViewAction
    data class ClickCategory(val categoryId: String) : PartnerOverviewViewAction
    data object InitView : PartnerOverviewViewAction
    data class SelectPartner(val partnerId: String) : PartnerOverviewViewAction
}