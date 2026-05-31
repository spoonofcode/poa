package com.spoonofcode.feature.partner.presentation.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerUseCase
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel(
    private val getPartnerUseCase: GetPartnerUseCase,
) :
    BaseViewModel<PartnerPanelViewState, PartnerPanelViewAction>(
        initialViewState = PartnerPanelViewState(),
        initialScreenState = ScreenState.CONTENT,
    ) {

    override fun onAction(action: PartnerPanelViewAction) {
        when (action) {
            PartnerPanelViewAction.NavigateToNotificationType -> navigateToNotificationType()
            PartnerPanelViewAction.NavigateToPartnerNotifications -> navigateToPartnerNotifications()
            PartnerPanelViewAction.NavigateToPartnerEdit -> navigateToPartnerEdit()
            PartnerPanelViewAction.NavigateToReports -> navigateToReports()
        }
    }

    fun navigateToPartnerEdit() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(PartnerModuleRoute.PartnerEdit))
        }
    }

    fun navigateToNotificationType() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(NotificationModuleRoute.NotificationType))
        }
    }

    fun navigateToPartnerNotifications() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(PartnerModuleRoute.PartnerNotifications))
        }
    }

    fun navigateToReports() {
        // TODO Add reports screen
    }
}