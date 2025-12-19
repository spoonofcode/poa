package com.spoonofcode.poa.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.usecase.SubscribeUserTopicsUseCase
import com.spoonofcode.poa.feature.reward.domain.usecase.AddRewardToUserUseCase
import com.spoonofcode.poa.feature.reward.domain.usecase.GetUserDailyCheckInStatusUseCase
import com.spoonofcode.poa.feature.reward.domain.usecase.GetUserRewardsPointsUseCase
import com.spoonofcode.poa.feature.reward.data.model.RewardType
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val subscribeUserTopicsUseCase: SubscribeUserTopicsUseCase,
    private val addRewardToUserUseCase: AddRewardToUserUseCase,
    private val getUserDailyCheckInStatusUseCase: GetUserDailyCheckInStatusUseCase,
    private val getUserRewardsPointsUseCase: GetUserRewardsPointsUseCase,
) : BaseViewModel<HomeViewState, HomeViewAction>(HomeViewState()) {

    override fun onAction(action: HomeViewAction) {
        when (action) {
            HomeViewAction.DailyCheckIn -> dailyCheckIn()
            HomeViewAction.GetNumberOfNewNotifications -> getNumberOfNewNotifications()
            HomeViewAction.InitView -> initView()
            HomeViewAction.NavigateToNotifications -> navigateToNotifications()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            //                subscribeUserTopicsUseCase()
            loadRewardsStatus()
        }
    }

    private suspend fun loadRewardsStatus() {
        getUserDailyCheckInStatusUseCase()
            .onSuccess {
                updateState {
                    copy(
                        dailyCheckInStatus = it
                    )
                }
            }
            .onFailure {
                showErrorView()
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
            }

        getUserRewardsPointsUseCase()
            .onSuccess {
                updateState {
                    copy(
                        totalPoints = it,
                        screenState = ScreenState.CONTENT,
                    )
                }
            }
            .onFailure {
                showErrorView()
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
            }
    }

    private fun dailyCheckIn() {
        viewModelScope.launch {
            showLoadingView()
            addRewardToUserUseCase(rewardType = RewardType.DAILY_CHECK_IN)
                .onSuccess {
                    showSnackbar(SnackbarEvent.Success(message = "Reward collected!"))
                    loadRewardsStatus()
                }
                .onFailure {
                    showSnackbar(SnackbarEvent.Error(message = "Error collecting reward: ${it.message}"))
                }
        }
    }

    private fun getNumberOfNewNotifications(): Int {
        // TODO: Implement logic to get the actual number of new notifications and move to viewState
        return 5
    }

    private fun navigateToNotifications() {
        viewModelScope.launch {
            viewModelNavigator.push(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewScreen())
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