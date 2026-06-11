package com.spoonofcode.feature.partner.presentation.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel :
    BaseViewModel<PartnerPanelViewState, PartnerPanelViewAction>(
        initialViewState = PartnerPanelViewState(),
        initialScreenState = ScreenState.CONTENT,
    ) {

    override fun onAction(action: PartnerPanelViewAction) {
        when (action) {
            PartnerPanelViewAction.NavigateToPartnerEdit -> navigateToPartnerEdit()
            PartnerPanelViewAction.NavigateToPartnerNotifications -> navigateToPartnerNotifications()
            PartnerPanelViewAction.NavigateToNotificationType -> navigateToNotificationType()
            PartnerPanelViewAction.NavigateToReports -> navigateToReports()
        }
    }

    private fun navigateToPartnerEdit() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(PartnerModuleRoute.PartnerEdit))
        }
    }

    fun navigateToNotificationType() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(PartnerModuleRoute.PartnerNotificationType))
        }
    }

    fun navigateToPartnerNotifications() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(PartnerModuleRoute.PartnerNotifications))
        }
    }

    private fun navigateToReports() {
        // TODO
    }
}
