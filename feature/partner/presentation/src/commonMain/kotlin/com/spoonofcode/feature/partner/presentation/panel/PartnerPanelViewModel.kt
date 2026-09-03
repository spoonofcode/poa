package com.spoonofcode.feature.partner.presentation.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.presentation.PartnerEvent
import com.spoonofcode.feature.partner.presentation.PartnerEventBus
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEvent
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEventBus
import com.spoonofcode.feature.partner.presentation.partner_data_updated
import com.spoonofcode.feature.partner.presentation.partner_notification_added
import com.spoonofcode.feature.partner.presentation.partner_notification_deleted
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

internal class PartnerPanelViewModel(
    private val partnerEventBus: PartnerEventBus,
    private val partnerNotificationEventBus: PartnerNotificationEventBus,
) :
    BaseViewModel<PartnerPanelViewState, PartnerPanelViewAction, Nothing>(
        initialViewState = ViewState.Content(PartnerPanelViewState),
        autoReloadData = true,
    ) {

    init {
        observePartnerEvents()
        observeNotificationEvents()
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
                    showSuccessSnackbar(getString(Res.string.partner_data_updated))
                }
            }
        }
    }

    private fun observeNotificationEvents() {
        viewModelScope.launch {
            partnerNotificationEventBus.overviewEvents.collect { event ->
                when (event) {
                    PartnerNotificationEvent.PartnerNotificationAdded -> {
                        showSuccessSnackbar(getString(Res.string.partner_notification_added))
                    }

                    PartnerNotificationEvent.PartnerNotificationDeleted -> {
                        showSuccessSnackbar(getString(Res.string.partner_notification_deleted))
                    }

                    else -> {}
                }
            }
        }
    }
}
