package com.spoonofcode.feature.notification.presentation.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.appnavigation.PartnerModule
import com.spoonofcode.feature.appnavigation.ProductModule
import com.spoonofcode.feature.notification.domain.model.NotificationType
import kotlinx.coroutines.launch

internal class NotificationTypeViewModel() :
    BaseViewModel<NotificationTypeViewState, NotificationTypeViewAction>(
        NotificationTypeViewState(),
        initialScreenState = ScreenState.CONTENT,
    ) {

    override fun onAction(action: NotificationTypeViewAction) {
        when (action) {
            NotificationTypeViewAction.SendNotificationToAllCustomers -> sendNotificationToAllCustomers()
            NotificationTypeViewAction.SendNotificationToSelectedPartnerCustomers -> sendNotificationToSelectedPartnerCustomers()
            NotificationTypeViewAction.SendNotificationToSelectedPartnerProductSeries -> sendNotificationToSelectedPartnerProductSeries()
            NotificationTypeViewAction.SendNotificationToAllPartnerCustomers -> sendNotificationToAllPartnerCustomers()
        }
    }

    private fun sendNotificationToAllCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(
                    NotificationModule.NotificationEditScreen(
                        notificationTypeName = NotificationType.global.name,
                    )
                )
            )
        }
    }

    private fun sendNotificationToSelectedPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(PartnerModule.CustomerOverviewScreen)
            )
        }
    }

    private fun sendNotificationToSelectedPartnerProductSeries() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(ProductModule.ProductSeriesOverviewScreen))
        }
    }

    private fun sendNotificationToAllPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(
                    NotificationModule.NotificationEditScreen(
                        notificationTypeName = NotificationType.partner.name,
                    )
                )
            )
        }
    }
}