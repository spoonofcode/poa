package com.spoonofcode.poa.feature.partner.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class PartnerEditViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val text: String = "",
    val partner: Partner? = null,
) : BaseViewState(screenState)