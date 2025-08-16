package com.spoonofcode.poa.feature.partner.panel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.partner.edit.PartnerEditScreen
import com.spoonofcode.poa.feature.product.series.overview.ProductSeriesOverviewScreen
import kotlinx.coroutines.launch

internal class PartnerPanelViewModel() :
    BaseViewModel<PartnerPanelViewState>(PartnerPanelViewState()) {

    fun navigateToPartnerEdit() {
        viewModelScope.launch {
            viewModelNavigator.push(PartnerEditScreen())
        }
    }

    fun navigateToPartnerProducts() {
        viewModelScope.launch {
            viewModelNavigator.push(ProductSeriesOverviewScreen())
        }
    }

}