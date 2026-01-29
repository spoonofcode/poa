package com.spoonofcode.feature.partner.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.partner.domain.model.Partner

@Immutable
internal data class PartnerDetailsViewState(
    val partner: Partner? = null,
) : BaseViewState()