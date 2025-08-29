package com.spoonofcode.poa.feature.partner.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.partner.mypartners.MyPartnersScreen
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel() :
    BaseViewModel<PartnerPanelViewState>(PartnerPanelViewState()) {

    fun navigateToMyPartners() {
        viewModelScope.launch {
            viewModelNavigator.push(MyPartnersScreen())
        }
    }

    fun navigateToPartnerProducts() {
        viewModelScope.launch {
        }
    }
}