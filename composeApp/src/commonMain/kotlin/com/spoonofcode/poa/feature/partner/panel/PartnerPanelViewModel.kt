package com.spoonofcode.poa.feature.partner.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.partner.mypartners.MyPartnersScreen // <-- Musimy nawigować do nowego ekranu listy
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel() :
    BaseViewModel<PartnerPanelViewState>(PartnerPanelViewState()) {

    // Zmieniamy nazwę, aby pasowała do tego, co robi: nawiguje do ekranu z listą
    fun navigateToMyPartners() {
        viewModelScope.launch {
            // Zamiast nawigować do edycji, nawigujemy do nowego ekranu listy
            viewModelNavigator.push(MyPartnersScreen())
        }
    }

    fun navigateToPartnerProducts() {
        viewModelScope.launch {
            // Tutaj w przyszłości będzie nawigacja do produktów partnera
        }
    }
}