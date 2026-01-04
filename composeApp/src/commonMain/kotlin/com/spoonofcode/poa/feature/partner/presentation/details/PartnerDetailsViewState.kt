package com.spoonofcode.poa.feature.partner.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class PartnerDetailsViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val partner: Partner? = null,
) : BaseViewState(screenState)