package com.spoonofcode.poa.feature.partner.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.core.ui.base.BaseViewState

@Immutable
internal data class PartnerEditViewState(
    val text: String = "",
    val partner: Partner? = null,
) : BaseViewState()