package com.spoonofcode.poa.feature.partner.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.notification.type.NotificationTypeScreen
import com.spoonofcode.poa.feature.partner.edit.PartnerEditScreen
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel() :
    BaseViewModel<PartnerPanelViewState>(PartnerPanelViewState()) {

    fun navigateToPartnerEdit() {
        viewModelScope.launch {
            viewModelNavigator.push(PartnerEditScreen(partnerId = "1"))
        }
    }

    fun navigateToNotificationType() {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationTypeScreen())
        }
    }
}