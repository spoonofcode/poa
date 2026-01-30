package com.spoonofcode.feature.appnavigation

sealed class PartnerModule : Screen() {
    data class PartnerDetailsScreen(
        val partnerId: String,
    ) : PartnerModule()

    data class PartnerEditScreen(
        val partnerId: String,
    ) : PartnerModule()

    data class CustomerOverviewScreen(
        val partnerId: String,
    ) : PartnerModule()

    object  PartnerPanelScreen : PartnerModule()
}