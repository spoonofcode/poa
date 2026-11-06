package com.spoonofcode.feature.partner.presentation.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.presentation.PartnerEvent
import com.spoonofcode.feature.partner.presentation.PartnerEventBus
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.partner_data_updated
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel(
    private val partnerEventBus: PartnerEventBus,
) : BaseViewModel<PartnerPanelViewState, PartnerPanelViewAction, Nothing>(
        initialViewState = ViewState.Content(PartnerPanelViewState),
    ) {
    init {
        observePartnerEvents()
    }

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

    private fun observePartnerEvents() {
        viewModelScope.launch {
            partnerEventBus.partnerUpdatedEvents.collect { event ->
                if (event is PartnerEvent.PartnerUpdated) {
                    showSuccessSnackbar(UiText.Resource(Res.string.partner_data_updated))
                }
            }
        }
    }
}
