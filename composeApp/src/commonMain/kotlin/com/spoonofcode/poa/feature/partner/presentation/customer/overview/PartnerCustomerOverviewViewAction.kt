package com.spoonofcode.poa.feature.partner.presentation.customer.overview

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerCustomerOverviewViewAction : BaseViewAction {
    data class CheckedCustomer(val userId: String) : PartnerCustomerOverviewViewAction
    data class InitView(val partnerId: String) : PartnerCustomerOverviewViewAction
    data object NavigateToNotification : PartnerCustomerOverviewViewAction
}