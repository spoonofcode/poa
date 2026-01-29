package com.spoonofcode.feature.partner.presentation.customer.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.poa.feature.partner.domain.model.PartnerUser

@Immutable
internal data class PartnerCustomerOverviewViewState(
    val partnerUsers: List<PartnerUser> = emptyList(),
    val checkedUserIds: Set<String> = emptySet(),
) : BaseViewState()