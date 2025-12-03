package com.spoonofcode.poa.feature.partner.customer.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class PartnerCustomerOverviewViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val partnerUsers: List<User> = emptyList(),
    val checkedUserIds: Set<String> = emptySet(),
) : BaseViewState(screenState)