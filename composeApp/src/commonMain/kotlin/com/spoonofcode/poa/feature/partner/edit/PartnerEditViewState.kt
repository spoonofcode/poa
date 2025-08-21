package com.spoonofcode.poa.feature.partner.edit

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class PartnerEditViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val text: String = ""
) : BaseViewState(screenState)