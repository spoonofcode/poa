package com.spoonofcode.feature.notification.presentation.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
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
                    NotificationModuleRoute.NotificationEdit(
                        notificationTypeName = NotificationType.global.name,
                        selectedTopics = setOf(NotificationType.global.name),
                        selectedNames = setOf("All Customers")
                    )
                )
            )
        }
    }

    private fun sendNotificationToSelectedPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(PartnerModuleRoute.CustomerOverview)
            )
        }
    }

    private fun sendNotificationToSelectedPartnerProductSeries() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(ProductModuleRoute.ProductSeriesOverview))
        }
    }

    private fun sendNotificationToAllPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(
                    NotificationModuleRoute.NotificationEdit(
                        notificationTypeName = NotificationType.partner.name,
                    )
                )
            )
        }
    }
}