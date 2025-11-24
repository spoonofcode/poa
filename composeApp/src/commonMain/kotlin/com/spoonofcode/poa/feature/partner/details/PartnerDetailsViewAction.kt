package com.spoonofcode.poa.feature.partner.details

sealed interface PartnerDetailsViewAction {
    data class InitView(val partnerId: String) : PartnerDetailsViewAction
}