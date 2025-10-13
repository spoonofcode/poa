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

//import com.spoonofcode.poa.feature.home.domain.GetDailyCheckInStatusUseCase
//import com.spoonofcode.poa.feature.home.domain.PerformDailyCheckInUseCase

internal class HomeViewModel(
    private val subscribeUserTopicsUseCase: SubscribeUserTopicsUseCase,
    //private val getDailyCheckInStatusUseCase: GetDailyCheckInStatusUseCase,
   // private val performDailyCheckInUseCase: PerformDailyCheckInUseCase,
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    fun initView() {
        subscribeNotifications()
        loadDailyRewardsStatus()
    }
    fun onCheckInClicked() {
        // Sprawdzamy, czy jakakolwiek nagroda jest dostępna
        val isRewardAvailable = viewState.value.rewards.any { it.status == RewardStatus.AVAILABLE_TO_CLAIM }
        if (!isRewardAvailable) return

        viewModelScope.launch {
            try {
                // ... Tutaj logika wywołania use case do odebrania nagrody ...

                // Po sukcesie, odświeżamy stan
                showSnackbar(SnackbarEvent.Success(message = "Reward collected!"))
                loadDailyRewardsStatus(isAfterClaim = true) // Przeładuj dane, symulując odbiór

            } catch (e: Exception) {
                showSnackbar(SnackbarEvent.Error(message = "Error collecting reward: ${e.message}"))
            }
        }
    }

    private fun loadDailyRewardsStatus(isAfterClaim: Boolean = false) {
        viewModelScope.launch {
            // Ta zmienna reprezentuje liczbę już odebranych dni.
            // Jeśli właśnie odebraliśmy nagrodę, liczba ta wzrasta.
            val claimedDays = if (isAfterClaim) 5 else 4
            val basePoints = 10
            val streakBonusPerDay = 5
            val weeklyBonus = 50

            val rewards = (1..16).map { day ->
                var points = basePoints + ((day - 1) * streakBonusPerDay)
                if (day > 0 && day % 7 == 0) {
                    points += weeklyBonus
                }

                // KLUCZOWA ZMIANA LOGIKI JEST TUTAJ
                val status = when {
                    // Dni do `claimedDays` są odebrane.
                    day <= claimedDays -> RewardStatus.CLAIMED
                    // NASTĘPNY dzień jest dostępny, ale TYLKO wtedy, gdy NIE jesteśmy zaraz po odbiorze.
                    day == claimedDays + 1 && !isAfterClaim -> RewardStatus.AVAILABLE_TO_CLAIM
                    // Wszystkie pozostałe dni są zablokowane.
                    else -> RewardStatus.LOCKED
                }
                DailyReward(day, points, status)
            }

            // Teraz ta logika zadziała poprawnie w obu przypadkach.
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