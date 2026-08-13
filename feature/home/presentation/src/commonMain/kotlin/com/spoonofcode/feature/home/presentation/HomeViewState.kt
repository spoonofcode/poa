package com.spoonofcode.feature.home.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.home.domain.model.DailyCheckInStatus

@Immutable
internal data class HomeViewState(
    val partnerImageLinks: List<String>,
    val dailyCheckInStatus: DailyCheckInStatus,
    val totalPoints: Int,
    val unreadNotificationsCount: Int,
) : BaseViewState()
