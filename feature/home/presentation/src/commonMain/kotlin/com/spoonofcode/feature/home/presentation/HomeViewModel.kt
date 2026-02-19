package com.spoonofcode.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.firebase.usecase.SubscribeUserTopicsUseCase
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.home.domain.model.RewardType
import com.spoonofcode.feature.home.domain.usecase.AddRewardToUserUseCase
import com.spoonofcode.feature.home.domain.usecase.GetUserDailyCheckInStatusUseCase
import com.spoonofcode.feature.home.domain.usecase.GetUserRewardsPointsUseCase
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationsUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetNewPartnersUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getNewPartnersUseCase: GetNewPartnersUseCase,
    private val subscribeUserTopicsUseCase: SubscribeUserTopicsUseCase,
    private val addRewardToUserUseCase: AddRewardToUserUseCase,
    private val getUserDailyCheckInStatusUseCase: GetUserDailyCheckInStatusUseCase,
    private val getUserRewardsPointsUseCase: GetUserRewardsPointsUseCase,
    private val getNotificationsUseCase: GetNotificationsUseCase,
) : BaseViewModel<HomeViewState, HomeViewAction>(HomeViewState()) {

    override fun onAction(action: HomeViewAction) {
        when (action) {
            HomeViewAction.DailyCheckIn -> dailyCheckIn()
            HomeViewAction.InitView -> initView()
            HomeViewAction.NavigateToNotifications -> navigateToNotifications()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            subscribeUserTopicsUseCase()
            val dailyCheckInStatusDeferred = async { getUserDailyCheckInStatusUseCase() }
            val totalPointsDeferred = async { getUserRewardsPointsUseCase() }
            val partnersDeferred = async { getNewPartnersUseCase() }
            val notificationsDeferred = async { getNotificationsUseCase() }

            val result = runCatching {
                val dailyCheckInStatus = dailyCheckInStatusDeferred.await().getOrThrow()
                val totalPoints = totalPointsDeferred.await().getOrThrow()
                val partnerImageLinks = partnersDeferred.await().getOrThrow()
                    .mapNotNull { partner -> partner.imageLink }
                val unreadNotificationsCount =
                    notificationsDeferred.await().getOrNull()?.count { !it.isRead } ?: 0

                Quadruple(
                    dailyCheckInStatus,
                    totalPoints,
                    partnerImageLinks,
                    unreadNotificationsCount
                )
            }

            result.onSuccess { (dailyCheckInStatus, totalPoints, partnerImageLinks, unreadCount) ->
                showContentView {
                    HomeViewState(
                        dailyCheckInStatus = dailyCheckInStatus,
                        totalPoints = totalPoints,
                        partnerImageLinks = partnerImageLinks,
                        unreadNotificationsCount = unreadCount,
                    )
                }
            }.onFailure {
                showErrorView()
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
            }
        }
    }

    private fun dailyCheckIn() {
        viewModelScope.launch {
            showLoadingView()
            addRewardToUserUseCase(rewardType = RewardType.DAILY_CHECK_IN)
                .onSuccess {
                    showSnackbar(SnackbarEvent.Success(message = "Reward collected!"))
                    initView()
                }
                .onFailure {
                    showSnackbar(SnackbarEvent.Error(message = "Error collecting reward: ${it.message}"))
                }
        }
    }

    private fun navigateToNotifications() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(NotificationModule.NotificationOverviewScreen))
        }
    }

    private data class Quadruple<out A, out B, out C, out D>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D
    )
}
