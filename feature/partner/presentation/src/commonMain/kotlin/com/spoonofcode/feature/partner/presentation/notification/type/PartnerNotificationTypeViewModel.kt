package com.spoonofcode.feature.partner.presentation.notification.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationTopicUseCase
import kotlinx.coroutines.launch

internal class PartnerNotificationTypeViewModel(
    private val getPartnerNotificationTopicUseCase: GetPartnerNotificationTopicUseCase,
) :
    BaseViewModel<PartnerNotificationTypeViewState, PartnerNotificationTypeViewAction>(
        PartnerNotificationTypeViewState(),
        initialScreenState = ScreenState.CONTENT,
    ) {

    override fun onAction(action: PartnerNotificationTypeViewAction) {
        when (action) {
            PartnerNotificationTypeViewAction.SendNotificationToAllCustomers -> sendNotificationToAllCustomers()
            PartnerNotificationTypeViewAction.SendNotificationToSelectedPartnerCustomers -> sendNotificationToSelectedPartnerCustomers()
            PartnerNotificationTypeViewAction.SendNotificationToSelectedPartnerProductSeries -> sendNotificationToSelectedPartnerProductSeries()
            PartnerNotificationTypeViewAction.SendNotificationToAllPartnerCustomers -> sendNotificationToAllPartnerCustomers()
        }
    }

    private fun sendNotificationToAllCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(
                    PartnerModuleRoute.PartnerNotificationEdit(
                        notificationTypeName = PartnerNotificationType.global.name,
                        selectedTopics = setOf(PartnerNotificationType.global.name),
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
            val partnerId = getPartnerNotificationTopicUseCase()
            viewModelNavigator.push(
                routeResolver.resolve(
                    PartnerModuleRoute.PartnerNotificationEdit(
                        notificationTypeName = PartnerNotificationType.partner.name,
                        selectedTopics = setOf(PartnerNotificationType.partner.createTopic(partnerId))
                    )
                )
            )
        }
    }
}
