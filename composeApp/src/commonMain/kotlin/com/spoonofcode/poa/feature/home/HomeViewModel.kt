package com.spoonofcode.poa.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.home.ClaimTodayRewardUseCase
import com.spoonofcode.poa.core.domain.home.GetDailyRewardsUseCase
import com.spoonofcode.poa.core.domain.user.GetUserPointsUseCase
import com.spoonofcode.poa.core.domain.user.SubscribeUserTopicsUseCase
import com.spoonofcode.poa.core.model.RewardStatus
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.core.ui.utils.LocalDateTimeUtils
import com.spoonofcode.poa.feature.notification.overview.NotificationOverviewScreen
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlin.time.ExperimentalTime

internal class HomeViewModel(
    private val subscribeUserTopicsUseCase: SubscribeUserTopicsUseCase,
    private val getDailyRewardsUseCase: GetDailyRewardsUseCase,
    private val claimTodayRewardUseCase: ClaimTodayRewardUseCase,
    private val getUserPointsUseCase: GetUserPointsUseCase,
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
        subscribeNotifications()
        loadDailyRewardsStatus()
    }

    private fun dailyCheckIn() {
        viewModelScope.launch {
            try {
                claimTodayRewardUseCase()
                showSnackbar(SnackbarEvent.Success(message = "Reward collected!"))
                loadDailyRewardsStatus()
            } catch (e: Exception) {
                showSnackbar(SnackbarEvent.Error(message = "Error collecting reward: ${e.message}"))
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

    @OptIn(ExperimentalTime::class)
    private fun loadDailyRewardsStatus() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val rewards = getDailyRewardsUseCase()
                val totalPoints = getUserPointsUseCase()
                val isRewardAvailable = rewards.any { it.status == RewardStatus.AVAILABLE_TO_CLAIM }

                val nextCheckInMillis =
                    if (!isRewardAvailable && rewards.any { it.status == RewardStatus.CLAIMED }) {
                        val localTimeZone = TimeZone.currentSystemDefault()
                        val today = LocalDateTimeUtils.now().date
                        val tomorrow = today.plus(1, DateTimeUnit.DAY)
                        val nextCheckInInstant = tomorrow.atStartOfDayIn(localTimeZone)
                        nextCheckInInstant.toEpochMilliseconds()
                    } else {
                        null
                    }

                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        rewards = rewards,
                        totalPoints = totalPoints,
                        nextCheckInTimeMillis = nextCheckInMillis
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorView()
                showSnackbar(SnackbarEvent.Error(message = "Failed to load daily rewards."))
            }
        }
    }

    private fun subscribeNotifications() {
        viewModelScope.launch {
            showLoadingView()
            try {
//                subscribeUserTopicsUseCase()
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