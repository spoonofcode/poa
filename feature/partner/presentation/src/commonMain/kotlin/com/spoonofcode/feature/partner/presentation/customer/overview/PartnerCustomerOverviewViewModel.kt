package com.spoonofcode.feature.partner.presentation.customer.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerCustomersUseCase
import kotlinx.coroutines.launch

internal class PartnerCustomerOverviewViewModel(
    private val getPartnerCustomersUseCase: GetPartnerCustomersUseCase,
) : BaseViewModel<PartnerCustomerOverviewViewState, PartnerCustomerOverviewViewAction, Nothing>() {
    override val startAction = {
        initView()
    }

    override fun onAction(action: PartnerCustomerOverviewViewAction) {
        when (action) {
            is PartnerCustomerOverviewViewAction.CheckedCustomer -> checkedCustomer(id = action.id)
            PartnerCustomerOverviewViewAction.NavigateToNotification -> navigateToNotification()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getPartnerCustomersUseCase()
                .onSuccess {
                    setContentState(
                        PartnerCustomerOverviewViewState(
                            customers = it,
                        )
                    )
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun checkedCustomer(id: String) {
        val selected = currentContentState().checkedUserIds.toMutableSet()
        val wasAdded = selected.contains(id)

        if (!wasAdded && selected.size >= MAX_SELECTED_USERS) {
            showSnackbar(
                SnackbarEvent.Error("You can select a maximum of $MAX_SELECTED_USERS items.")
            )
            return
        }

        if (wasAdded) selected.remove(id) else selected.add(id)

        updateContentState { copy(checkedUserIds = selected) }
    }

    private fun navigateToNotification() {
        viewModelScope.launch {
            viewModelNavigator.push(
                PartnerModuleRoute.PartnerNotificationEdit(
                    selectedTopics = currentContentState().checkedUserIds.map {
                        PartnerNotificationType.user.createTopic(it)
                    }.toSet(),
                    selectedNames = currentContentState().customers
                        .filter { it.id in currentContentState().checkedUserIds }
                        .map { it.name }.toSet()
                )
            )
        }
    }

    private companion object {
        private const val MAX_SELECTED_USERS = 5
    }
}
