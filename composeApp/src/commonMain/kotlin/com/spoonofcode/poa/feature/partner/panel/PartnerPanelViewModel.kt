package com.spoonofcode.poa.feature.partner.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.user.GetUserPartnersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.notification.type.NotificationTypeScreen
import com.spoonofcode.poa.feature.partner.edit.PartnerEditScreen
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel(
    private val getUserPartnersUseCase: GetUserPartnersUseCase,
) :
    BaseViewModel<PartnerPanelViewState>(PartnerPanelViewState()) {

    fun navigateToPartnerEdit() {
        viewModelScope.launch {
            viewModelNavigator.push(PartnerEditScreen(partnerId = getUserPartnersUseCase().first().id))
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