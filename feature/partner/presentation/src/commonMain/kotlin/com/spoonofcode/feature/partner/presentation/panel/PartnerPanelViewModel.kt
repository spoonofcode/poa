package com.spoonofcode.feature.partner.presentation.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditScreen
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel() :
    BaseViewModel<PartnerPanelViewState, PartnerPanelViewAction>(
        initialViewState = PartnerPanelViewState(),
        initialScreenState = ScreenState.CONTENT,
    ) {

    override fun onAction(action: PartnerPanelViewAction) {
        when (action) {
            PartnerPanelViewAction.NavigateToNotificationType -> navigateToNotificationType()
            PartnerPanelViewAction.NavigateToPartnerEdit -> navigateToPartnerEdit()
            PartnerPanelViewAction.NavigateToReports -> navigateToReports()
        }
    }

    fun navigateToPartnerEdit() {
        // TODO #99 Adopt poa backend - read partner id from profile backend
        val partnerId = "4KJzqfBSQ1WbwysYa0j9"
        viewModelScope.launch {
            viewModelNavigator.push(PartnerEditScreen(partnerId = partnerId))
        }
    }

    fun navigateToNotificationType() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(NotificationModule.NotificationTypeScreen))
        }
    }

    fun navigateToReports() {
        // TODO Add reports screen
    }
}