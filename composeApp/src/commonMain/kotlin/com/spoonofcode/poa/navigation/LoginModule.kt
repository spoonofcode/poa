package com.spoonofcode.poa.navigation

sealed class LoginModule : Screen() {
    data class ExchangeRateDetailsScreen(
        val rateCode: String,
        val tableCode: String,
        val currency: String
    ) : LoginModule()

    object ExchangeRateOverviewScreen : LoginModule()
}