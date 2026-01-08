package com.spoonofcode.poa.feature.partner.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.feature.partner.domain.model.Partner

@Immutable
internal data class PartnerDetailsViewState(
    val partner: Partner? = null,
) : BaseViewState()