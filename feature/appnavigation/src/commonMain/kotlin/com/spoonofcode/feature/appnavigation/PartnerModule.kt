package com.spoonofcode.feature.appnavigation

sealed class PartnerModule : Screen() {
    data class PartnerDetailsScreen(
        val partnerId: String,
    ) : PartnerModule()

    data class PartnerEditScreen(
        val partnerId: String,
    ) : PartnerModule()

    object CustomerOverviewScreen: PartnerModule()

    object  PartnerPanelScreen : PartnerModule()
}