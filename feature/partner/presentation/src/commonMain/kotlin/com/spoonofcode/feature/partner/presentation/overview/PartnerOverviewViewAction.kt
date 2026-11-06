package com.spoonofcode.feature.partner.presentation.overview

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerOverviewViewAction : BaseViewAction {
    data class ChangeSearchText(
        val searchText: String,
    ) : PartnerOverviewViewAction

    data class ClickCategory(
        val categoryName: String,
    ) : PartnerOverviewViewAction

    data class SelectPartner(
        val partnerId: String,
    ) : PartnerOverviewViewAction
}
