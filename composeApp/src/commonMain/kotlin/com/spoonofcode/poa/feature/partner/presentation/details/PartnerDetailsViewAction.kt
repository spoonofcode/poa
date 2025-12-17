package com.spoonofcode.poa.feature.partner.presentation.details

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface PartnerDetailsViewAction: BaseViewAction {
    data class InitView(val partnerId: String) :
        com.spoonofcode.poa.feature.partner.presentation.details.PartnerDetailsViewAction
}