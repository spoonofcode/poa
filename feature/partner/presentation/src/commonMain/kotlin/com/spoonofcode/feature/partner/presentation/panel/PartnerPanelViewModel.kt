package com.spoonofcode.feature.partner.presentation.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel :
    BaseViewModel<PartnerPanelViewState, PartnerPanelViewAction, Nothing>(
        initialViewState = ViewState.Content(PartnerPanelViewState()),
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
            viewModelNavigator.push(PartnerModuleRoute.PartnerEdit)
        }
    }

    fun navigateToNotificationType() {
        viewModelScope.launch {
            viewModelNavigator.push(PartnerModuleRoute.PartnerNotificationType)
        }
    }

    fun navigateToPartnerNotifications() {
        viewModelScope.launch {
            viewModelNavigator.push(PartnerModuleRoute.PartnerNotifications)
        }
    }

    private fun navigateToReports() {
        // TODO
    }
}
