package com.spoonofcode.poa.feature.notification.presentation.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.user.partner.GetUserPartnersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen
import com.spoonofcode.poa.feature.partner.presentation.customer.overview.CustomerOverviewScreen
import com.spoonofcode.poa.feature.product.presentation.series.overview.ProductSeriesOverviewScreen
import kotlinx.coroutines.launch

internal class NotificationTypeViewModel(
    private val getUserPartnersUseCase: GetUserPartnersUseCase,
) : BaseViewModel<com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewState, com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction>(
    _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewState()
) {

    override fun onAction(action: com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction) {
        when (action) {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToAllCustomers -> navigateToAllCustomers()
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToPartnerCustomers -> navigateToPartnerCustomers()
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToPartnerProductSeries -> navigateToPartnerProductSeries()
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToAllPartnerCustomers -> navigateToAllPartnerCustomers()
        }
    }

    private fun navigateToAllCustomers() {
        viewModelScope.launch {
            _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.viewModelNavigator.push(
                _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen()
            )
        }
    }

    private fun navigateToPartnerCustomers() {
        viewModelScope.launch {
            getUserPartnersUseCase()
                .onSuccess {
                    val partnerId = it.first().id
                    _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.viewModelNavigator.push(
                        CustomerOverviewScreen(
                            partnerId = partnerId
                        )
                    )
                }
                .onFailure {
                    _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.showSnackbar(
                        SnackbarEvent.Error(message = "ERROR: $it")
                    )
                }
        }
    }

    private fun navigateToPartnerProductSeries() {
        viewModelScope.launch {
            _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.viewModelNavigator.push(ProductSeriesOverviewScreen())
        }
    }

    private fun navigateToAllPartnerCustomers() {
        viewModelScope.launch {
            _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.viewModelNavigator.push(
                _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen()
            )
        }
    }
}