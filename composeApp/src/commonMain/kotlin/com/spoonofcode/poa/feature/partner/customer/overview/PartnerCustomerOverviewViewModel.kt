package com.spoonofcode.poa.feature.partner.customer.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetPartnerUsersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class PartnerCustomerOverviewViewModel(
    private val getPartnerUsersUseCase: GetPartnerUsersUseCase,
) : BaseViewModel<PartnerCustomerOverviewViewState, PartnerCustomerOverviewViewAction>(PartnerCustomerOverviewViewState()) {

    override fun onAction(action: PartnerCustomerOverviewViewAction) {
        when (action) {
            is PartnerCustomerOverviewViewAction.CheckedCustomer -> checkedCustomer(userId = action.userId)
            is PartnerCustomerOverviewViewAction.InitView -> initView(partnerId = action.partnerId)
            PartnerCustomerOverviewViewAction.NavigateToNotification -> navigateToNotification()
        }
    }

    private fun initView(partnerId: String) {
        showLoadingView()
        viewModelScope.launch {
            try {
                val partnerUsers = getPartnerUsersUseCase(partnerId)
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        partnerUsers = partnerUsers,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
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

        updateState { copy(checkedUserIds = selected) }
    }

    private fun navigateToNotification() {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationEditScreen(
                    topics = getSelectedUsers(),
                    itemIds = getSelectedNames()
                )
            )
        }
    }

    private fun getSelectedUsers(): List<String> =
        currentState().checkedUserIds.map { userId -> "userId$userId" }.toList()

    private fun getSelectedNames(): List<String> =
        currentState().partnerUsers.filter { it.id in currentState().checkedUserIds }
            .map { user -> user.fullName }.toList()

    private fun showLoadingView() {
        updateState {
            copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                screenState = ScreenState.ERROR,
            )
        }
    }

    private companion object {
        private const val MAX_SELECTED_USERS = 5
    }
}