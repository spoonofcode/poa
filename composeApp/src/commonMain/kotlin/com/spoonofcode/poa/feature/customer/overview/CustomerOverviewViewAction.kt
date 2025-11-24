package com.spoonofcode.poa.feature.customer.overview

sealed interface CustomerOverviewViewAction {
    data class CheckedCustomer(val userId: String) : CustomerOverviewViewAction
    data class InitView(val partnerId: String) : CustomerOverviewViewAction
    data object NavigateToNotification : CustomerOverviewViewAction
}