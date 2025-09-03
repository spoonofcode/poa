package com.spoonofcode.poa.feature.partner.mypartners

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
data class MyPartnersViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val partners: List<Partner> = emptyList()
) : BaseViewState(screenState)