package com.spoonofcode.feature.partner.presentation.customer.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerCustomersUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerUsersUseCase
import kotlinx.coroutines.launch

internal class PartnerCustomerOverviewViewModel(
    private val getPartnerUsersUseCase: GetPartnerUsersUseCase,
    private val getPartnerCustomersUseCase: GetPartnerCustomersUseCase,
) : BaseViewModel<PartnerCustomerOverviewViewState, PartnerCustomerOverviewViewAction>(
    PartnerCustomerOverviewViewState()
) {

    override fun onAction(action: PartnerCustomerOverviewViewAction) {
        when (action) {
            is PartnerCustomerOverviewViewAction.CheckedCustomer -> checkedCustomer(userId = action.customerId)
            is PartnerCustomerOverviewViewAction.InitView -> initView(partnerId = action.partnerId)
            PartnerCustomerOverviewViewAction.NavigateToNotification -> navigateToNotification()
        }
    }

    private fun initView(partnerId: String) {
        showLoadingView()
        viewModelScope.launch {
            getPartnerCustomersUseCase()
                .onSuccess {
                    showContentView {
                        PartnerCustomerOverviewViewState(
                            customers = it,
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun checkedCustomer(userId: String) {
        val selected = currentState().checkedUserIds.toMutableSet()
        val wasAdded = selected.contains(userId)

        if (!wasAdded && selected.size >= MAX_SELECTED_USERS) {
            showSnackbar(
                SnackbarEvent.Error("You can select a maximum of $MAX_SELECTED_USERS items.")
            )
            return
        }

        if (wasAdded) selected.remove(userId) else selected.add(userId)

        updateState { PartnerCustomerOverviewViewState(checkedUserIds = selected) }
    }

    private fun navigateToNotification() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(
                    NotificationModule.NotificationEditScreen(
                        topics = getSelectedUsers(),
                        itemIds = getSelectedNames()
                    )
                )
            )
        }
    }

    private fun getSelectedUsers(): List<String> =
        currentState().checkedUserIds.map { userId -> "userId$userId" }.toList()

    private fun getSelectedNames(): List<String> =
        currentState().customers.filter { it.id in currentState().checkedUserIds }
            .map { customer -> customer.name }.toList()

    private companion object {
        private const val MAX_SELECTED_USERS = 5
    }
}