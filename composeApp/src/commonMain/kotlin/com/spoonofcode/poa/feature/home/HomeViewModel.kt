package com.spoonofcode.poa.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.user.SubscribeUserTopicsUseCase
import com.spoonofcode.poa.core.domain.user.reward.AddRewardToUserUseCase
import com.spoonofcode.poa.core.domain.user.reward.GetUserDailyCheckInStatusUseCase
import com.spoonofcode.poa.core.domain.user.reward.GetUserRewardsPointsUseCase
import com.spoonofcode.poa.core.model.RewardType
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.overview.NotificationOverviewScreen
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
            viewModelNavigator.push(NotificationOverviewScreen())
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