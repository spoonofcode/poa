package com.spoonofcode.feature.appnavigation

sealed class PartnerModuleRoute : ModuleRoute() {
    data class PartnerDetails(
        val partnerId: String,
    ) : PartnerModuleRoute()

    data object PartnerEdit : PartnerModuleRoute()
    data object CustomerOverview : PartnerModuleRoute()
    data object PartnerPanel : PartnerModuleRoute()
    data object PartnerNotifications : PartnerModuleRoute()
}