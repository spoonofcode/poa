package com.spoonofcode.feature.partner.presentation.customer.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.partner.domain.model.Customer

@Immutable
internal data class PartnerCustomerOverviewViewState(
    val customers: List<Customer>,
    val checkedUserIds: Set<String> = emptySet(),
) : BaseViewState()
