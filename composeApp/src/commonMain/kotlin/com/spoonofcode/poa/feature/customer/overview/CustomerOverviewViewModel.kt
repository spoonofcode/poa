package com.spoonofcode.poa.feature.customer.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetPartnerUsersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class CustomerOverviewViewModel(
    private val getPartnerUsersUseCase: GetPartnerUsersUseCase,
) : BaseViewModel<CustomerOverviewViewState>(CustomerOverviewViewState()) {

    fun initView(partnerId: Int) {
        viewModelScope.launch {
            showLoadingView()
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

    fun checkedCustomer(userId: Int) {
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

    fun navigateToNotification() {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationEditScreen(
                    itemIds = getSelectedUsers()
                )
            )
        }
    }

    private fun getSelectedUsers(): List<String> =
        currentState().checkedUserIds.map { userId -> "userId$userId" }.toList()

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