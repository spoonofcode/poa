package com.spoonofcode.poa.feature.home

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.DailyReward
import com.spoonofcode.poa.core.model.RewardStatus
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class HomeViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val partnerImageLinks: List<String> = listOf(
        "https://proof-of-wear.com/images/logo.jpg",
        "https://beautysaute.pl/environment/cache/images/0_0_storefrontImages_11a16d30-d85a-422f-917d-195c30274147.png",
        "https://proof-of-wear.com/images/logo.jpg",
    ),
    val rewards: List<DailyReward> = emptyList(),
    val nextCheckInTimeMillis: Long? = null,
) : BaseViewState(screenState)