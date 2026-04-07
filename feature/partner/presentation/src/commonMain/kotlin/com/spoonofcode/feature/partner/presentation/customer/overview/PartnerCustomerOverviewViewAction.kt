package com.spoonofcode.feature.partner.presentation.customer.overview

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerCustomerOverviewViewAction : BaseViewAction {
    data class CheckedCustomer(val email: String) : PartnerCustomerOverviewViewAction
    data object InitView : PartnerCustomerOverviewViewAction
    data object NavigateToNotification : PartnerCustomerOverviewViewAction
}