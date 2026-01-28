package com.spoonofcode.poa.navigation

import com.spoonofcode.core.presentation.navigation.Screen

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