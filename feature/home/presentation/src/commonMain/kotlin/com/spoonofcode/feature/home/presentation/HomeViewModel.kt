package com.spoonofcode.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
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
    private val addRewardToUserUseCase: AddRewardToUserUseCase,
    private val getUserDailyCheckInStatusUseCase: GetUserDailyCheckInStatusUseCase,
    private val getUserRewardsPointsUseCase: GetUserRewardsPointsUseCase,
    private val getNotificationsUseCase: GetNotificationsUseCase,
) : BaseViewModel<HomeViewState, HomeViewAction, Nothing>() {
    override val startAction = {
        initView()
    }

    override fun onAction(action: HomeViewAction) {
        when (action) {
            HomeViewAction.DailyCheckIn -> dailyCheckIn()
            HomeViewAction.NavigateToNotifications -> navigateToNotifications()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
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
                setContentState(
                    HomeViewState(
                        dailyCheckInStatus = dailyCheckInStatus,
                        totalPoints = totalPoints,
                        partnerImageLinks = partnerImageLinks,
                        unreadNotificationsCount = unreadCount,
                    )
                )
                showContentView()
            }.onFailure {
                showErrorView()
                showErrorSnackbar(message = "ERROR: $it")
            }
        }
    }

    private fun dailyCheckIn() {
        viewModelScope.launch {
            showLoadingView()
            addRewardToUserUseCase(rewardType = RewardType.DAILY_CHECK_IN)
                .onSuccess {
//                    showSuccessSnackbar(UiText.Resource("Reward collected!"))
                    initView()
                }
                .onFailure {
                    showErrorSnackbar(message = "Error collecting reward: ${it.message}")
                }
        }
    }

    private fun navigateToNotifications() {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationModuleRoute.NotificationOverview)
        }
    }

    private data class Quadruple<out A, out B, out C, out D>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D
    )
}
