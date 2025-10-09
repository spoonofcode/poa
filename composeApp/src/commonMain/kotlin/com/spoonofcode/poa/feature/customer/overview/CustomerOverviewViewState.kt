package com.spoonofcode.poa.feature.customer.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class CustomerOverviewViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val partnerUsers: List<User> = emptyList(),
    val checkedUserIds: Set<Int> = emptySet(),
) : BaseViewState(screenState)