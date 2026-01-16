package com.spoonofcode.poa.feature.partner.presentation.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.feature.partner.domain.usecase.GetUserPartnersUseCase
import com.spoonofcode.poa.core.ui.base.BaseViewModel
import com.spoonofcode.poa.core.ui.snackbar.SnackbarEvent
import com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeScreen
import com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditScreen
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel(
    private val getUserPartnersUseCase: GetUserPartnersUseCase,
) :
    BaseViewModel<PartnerPanelViewState, PartnerPanelViewAction>(PartnerPanelViewState()) {

    override fun onAction(action: PartnerPanelViewAction) {
        when (action) {
            PartnerPanelViewAction.NavigateToNotificationType -> navigateToNotificationType()
            PartnerPanelViewAction.NavigateToPartnerEdit -> navigateToPartnerEdit()
            PartnerPanelViewAction.NavigateToReports-> navigateToReports()
        }
    }

    fun navigateToPartnerEdit() {
        viewModelScope.launch {
            getUserPartnersUseCase()
                .onSuccess {
                    viewModelNavigator.push(PartnerEditScreen(partnerId = it.first().id))
                }
                .onFailure {
                    showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
                }
        }
    }

    fun navigateToNotificationType() {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationTypeScreen())
        }
    }

    fun navigateToReports() {
        // TODO Add reports screen
    }
}