package com.spoonofcode.poa.feature.partner.mypartners

import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

data class MyPartnersViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val partners: List<Partner> = emptyList()
) : BaseViewState(screenState)