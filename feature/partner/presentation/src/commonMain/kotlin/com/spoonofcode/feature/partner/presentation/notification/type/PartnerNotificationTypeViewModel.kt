package com.spoonofcode.feature.partner.presentation.notification.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationTopicUseCase
import kotlinx.coroutines.launch

internal class PartnerNotificationTypeViewModel(
    private val getPartnerNotificationTopicUseCase: GetPartnerNotificationTopicUseCase,
) : BaseViewModel<PartnerNotificationTypeViewState, PartnerNotificationTypeViewAction, Nothing>(
        initialViewState = ViewState.Content(PartnerNotificationTypeViewState()),
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
                PartnerModuleRoute.PartnerNotificationEdit(
                    selectedTopics = setOf(PartnerNotificationType.GLOBAL.name),
                ),
            )
        }
    }

    private fun sendNotificationToSelectedPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                PartnerModuleRoute.CustomerOverview,
            )
        }
    }

    private fun sendNotificationToSelectedPartnerProductSeries() {
        viewModelScope.launch {
            viewModelNavigator.push(ProductModuleRoute.ProductSeriesOverview)
        }
    }

    private fun sendNotificationToAllPartnerCustomers() {
        viewModelScope.launch {
            val partnerId = getPartnerNotificationTopicUseCase()
            viewModelNavigator.push(
                PartnerModuleRoute.PartnerNotificationEdit(
                    selectedTopics = setOf(PartnerNotificationType.PARTNER.createTopic(partnerId)),
                ),
            )
        }
    }
}
