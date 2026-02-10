package com.spoonofcode.feature.partner.presentation.panel

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class PartnerPanelViewState(
    val text: String = ""
) : BaseViewState()