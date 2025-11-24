package com.spoonofcode.poa.feature.partner.details

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface PartnerDetailsViewAction: BaseViewAction {
    data class InitView(val partnerId: String) : PartnerDetailsViewAction
}