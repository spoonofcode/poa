package com.spoonofcode.feature.appnavigation

sealed class PartnerModuleRoute : ModuleRoute() {
    data class PartnerDetails(
        val partnerId: String,
    ) : PartnerModuleRoute()

    data class PartnerEdit(
        val partnerId: String,
    ) : PartnerModuleRoute()

    object CustomerOverview: PartnerModuleRoute()

    object  PartnerPanel : PartnerModuleRoute()
}