package com.spoonofcode.poa.feature.partner.presentation.panel

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState

@Immutable
internal data class PartnerPanelViewState(
    val text: String = ""
) : BaseViewState()