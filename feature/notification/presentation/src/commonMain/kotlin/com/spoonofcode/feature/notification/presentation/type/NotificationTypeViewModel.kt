package com.spoonofcode.feature.notification.presentation.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.notification.presentation.edit.NotificationEditScreen
import kotlinx.coroutines.launch

internal class NotificationTypeViewModel() : BaseViewModel<NotificationTypeViewState, NotificationTypeViewAction>(
    NotificationTypeViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: NotificationTypeViewAction) {
        when (action) {
            NotificationTypeViewAction.NavigateToAllCustomers -> navigateToAllCustomers()
            NotificationTypeViewAction.NavigateToPartnerCustomers -> navigateToPartnerCustomers()
            NotificationTypeViewAction.NavigateToPartnerProductSeries -> navigateToPartnerProductSeries()
            NotificationTypeViewAction.NavigateToAllPartnerCustomers -> navigateToAllPartnerCustomers()
        }
    }

    private fun navigateToAllCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationEditScreen()
            )
        }
    }

    private fun navigateToPartnerCustomers() {
        // TODO #99 Adopt poa backend - read partner id from profile backend
        val partnerId = "4KJzqfBSQ1WbwysYa0j9"
// TODO #109 Fix after migration to feature modules
//        viewModelScope.launch {
//            viewModelNavigator.push(
//                CustomerOverviewScreen(
//                    partnerId = partnerId
//                )
//            )
//        }
    }

    private fun navigateToPartnerProductSeries() {
        // TODO #109 Fix after migration to feature modules
//        viewModelScope.launch {
//            viewModelNavigator.push(ProductSeriesOverviewScreen())
//        }
    }

    private fun navigateToAllPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationEditScreen()
            )
        }
    }
}