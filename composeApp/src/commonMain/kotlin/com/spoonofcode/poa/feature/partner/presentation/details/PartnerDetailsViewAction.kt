package com.spoonofcode.poa.feature.partner.presentation.details

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerDetailsViewAction: BaseViewAction {
    data class InitView(val partnerId: String) :
        com.spoonofcode.poa.feature.partner.presentation.details.PartnerDetailsViewAction
}