package com.spoonofcode.feature.partner.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class PartnerEditViewState(
    val text: String = "",
    val partner: Partner? = null,
) : BaseViewState()