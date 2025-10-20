@file:OptIn(ExperimentalTime::class)
package com.spoonofcode.poa.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.home.ClaimTodayRewardUseCase
import com.spoonofcode.poa.core.domain.home.GetDailyRewardsUseCase
import com.spoonofcode.poa.core.domain.user.SubscribeUserTopicsUseCase
import com.spoonofcode.poa.core.model.RewardStatus
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.overview.NotificationOverviewScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.Job
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus


internal class HomeViewModel(
    private val subscribeUserTopicsUseCase: SubscribeUserTopicsUseCase,
    private val getDailyRewardsUseCase: GetDailyRewardsUseCase,
    private val claimTodayRewardUseCase: ClaimTodayRewardUseCase,
) : BaseViewModel<HomeViewState>(HomeViewState()) {
    private var timerJob: Job? = null
    fun initView() {
    //    subscribeNotifications()
        loadDailyRewardsStatus()
    }

    fun loadDailyRewardsStatus() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val rewards = getDailyRewardsUseCase()
                val isRewardAvailable = rewards.any { it.status == RewardStatus.AVAILABLE_TO_CLAIM }

                val nextCheckInMillis = if (!isRewardAvailable && rewards.any { it.status == RewardStatus.CLAIMED }) {
                    val now = Clock.System.now()
                    val localTimeZone = TimeZone.currentSystemDefault()
                    val today = now.toLocalDateTime(localTimeZone).date
                    val tomorrow = today.plus(1, DateTimeUnit.DAY)
                    val nextCheckInInstant = tomorrow.atStartOfDayIn(localTimeZone)

                    // >>> LOG 5: Sprawdzenie obliczonego czasu w ViewModelu
                    println(">>> LOG (VM): Nagroda odebrana. Następny check-in o: ${nextCheckInInstant.toEpochMilliseconds()} (${nextCheckInInstant})")

                    nextCheckInInstant.toEpochMilliseconds()
                } else {
                    // >>> LOG 6: Sprawdzenie, kiedy czas nie jest ustawiany
                    println(">>> LOG (VM): Nagroda dostępna lub żadna nieodebrana. Zegar nie jest potrzebny (nextCheckInMillis = null).")
                    null
                }

                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        rewards = rewards,
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

    fun onCheckInClicked() {
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