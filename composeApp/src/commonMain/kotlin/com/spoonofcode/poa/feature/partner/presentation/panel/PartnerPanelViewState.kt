package com.spoonofcode.poa.feature.partner.presentation.panel

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.base.BaseViewState

@Immutable
internal data class PartnerPanelViewState(
    val text: String = ""
) : BaseViewState()