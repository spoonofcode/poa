package com.spoonofcode.poa.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.user.SubscribeUserTopicsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.overview.NotificationOverviewScreen
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val subscribeUserTopicsUseCase: SubscribeUserTopicsUseCase,
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    fun initView() {
        subscribeNotifications()
    }

    fun getNumberOfNewNotifications(): Int {
        // TODO: Implement logic to get the actual number of new notifications
        return 5
    }

    fun navigateToNotifications() {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationOverviewScreen())
        }
    }

    fun subscribeNotifications() {
        viewModelScope.launch {
            showLoadingView()
            try {
                subscribeUserTopicsUseCase()
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                    )
                }
            } catch (e: Exception) {
                showErrorView()
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
            }

        }
    }

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
}