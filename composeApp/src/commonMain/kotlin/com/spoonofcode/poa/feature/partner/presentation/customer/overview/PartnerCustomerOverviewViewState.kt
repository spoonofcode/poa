package com.spoonofcode.poa.feature.partner.presentation.customer.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.feature.partner.domain.model.PartnerUser

@Immutable
internal data class PartnerCustomerOverviewViewState(
    val partnerUsers: List<PartnerUser> = emptyList(),
    val checkedUserIds: Set<String> = emptySet(),
) : BaseViewState()