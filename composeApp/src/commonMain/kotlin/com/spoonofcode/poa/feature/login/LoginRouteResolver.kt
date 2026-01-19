package com.spoonofcode.poa.feature.login

import com.spoonofcode.poa.core.ui.navigation.route.ModuleRouteResolver
import com.spoonofcode.poa.navigation.LoginModule
import com.spoonofcode.poa.navigation.Screen

internal class LoginRouteResolver : ModuleRouteResolver<LoginModule> {

    override fun resolve(screen: Screen): cafe.adriel.voyager.core.screen.Screen? =
        (screen as? ExchangeRateModule)?.let {
            when (it) {
                is ExchangeRateModule.ExchangeRateDetailsScreen -> ExchangeRateDetailsScreen(
                    rateCode = it.rateCode,
                    tableCode = it.tableCode,
                    currency = it.currency,
                )

                ExchangeRateModule.ExchangeRateOverviewScreen -> ExchangeRateOverviewScreen()
            }
        }
}