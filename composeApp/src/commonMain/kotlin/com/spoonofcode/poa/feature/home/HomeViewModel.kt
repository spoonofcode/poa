@file:OptIn(ExperimentalTime::class)
package com.spoonofcode.poa.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.user.SubscribeUserTopicsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.overview.NotificationOverviewScreen
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.ExperimentalTime

internal class HomeViewModel(
    private val subscribeUserTopicsUseCase: SubscribeUserTopicsUseCase,
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    fun initView() {
        subscribeNotifications()
        loadDailyRewardsStatus()
    }
    fun onCheckInClicked() {
        val isRewardAvailable = viewState.value.rewards.any { it.status == RewardStatus.AVAILABLE_TO_CLAIM }
        if (!isRewardAvailable) return

        viewModelScope.launch {
            try {
                showSnackbar(SnackbarEvent.Success(message = "Reward collected!"))
                loadDailyRewardsStatus(isAfterClaim = true)

            } catch (e: Exception) {
                showSnackbar(SnackbarEvent.Error(message = "Error collecting reward: ${e.message}"))
            }
        }
    }

    private fun loadDailyRewardsStatus(isAfterClaim: Boolean = false) {
        viewModelScope.launch {
            val claimedDays = if (isAfterClaim) 5 else 4
            val basePoints = 10
            val streakBonusPerDay = 5
            val weeklyBonus = 50

            val rewards = (1..16).map { day ->
                var points = basePoints + ((day - 1) * streakBonusPerDay)
                if (day > 0 && day % 7 == 0) {
                    points += weeklyBonus
                }
                val status = when {
                    day <= claimedDays -> RewardStatus.CLAIMED
                    day == claimedDays + 1 && !isAfterClaim -> RewardStatus.AVAILABLE_TO_CLAIM
                    else -> RewardStatus.LOCKED
                }
                DailyReward(day, points, status)
            }

            val isRewardAvailable = rewards.any { it.status == RewardStatus.AVAILABLE_TO_CLAIM }
            val nextCheckInMillis = if (!isRewardAvailable) {
                Clock.System.now().toEpochMilliseconds() + 5.hours.inWholeMilliseconds
            } else {
                null
            }

            updateState {
                copy(
                    rewards = rewards,
                    nextCheckInTimeMillis = nextCheckInMillis
                )
            }
        }
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