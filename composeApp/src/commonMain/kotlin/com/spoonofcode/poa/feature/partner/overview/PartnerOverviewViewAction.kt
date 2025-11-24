package com.spoonofcode.poa.feature.partner.overview

sealed interface PartnerOverviewViewAction {
    data class ChangeSearchText(val searchText: String) : PartnerOverviewViewAction
    data class ClickCategory(val categoryId: String) : PartnerOverviewViewAction
    data object InitView : PartnerOverviewViewAction
    data class SelectPartner(val partnerId: String) : PartnerOverviewViewAction
}