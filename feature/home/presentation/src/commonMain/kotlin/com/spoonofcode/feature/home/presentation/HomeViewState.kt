package com.spoonofcode.feature.home.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.home.domain.model.DailyCheckInStatus

@Immutable
internal data class HomeViewState(
    val partnerImageLinks: List<String> = emptyList(),
    val dailyCheckInStatus: DailyCheckInStatus? = null,
    val totalPoints: Int = 0,
) : BaseViewState()