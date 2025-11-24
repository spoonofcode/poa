package com.spoonofcode.poa.feature.customer.overview

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface CustomerOverviewViewAction: BaseViewAction {
    data class CheckedCustomer(val userId: String) : CustomerOverviewViewAction
    data class InitView(val partnerId: String) : CustomerOverviewViewAction
    data object NavigateToNotification : CustomerOverviewViewAction
}