package com.spoonofcode.poa.app.di

import com.spoonofcode.poa.core.data.di.dataModule
import com.spoonofcode.poa.core.domain.di.domainModule
import com.spoonofcode.poa.core.network.di.networkModule
import com.spoonofcode.poa.core.settings.di.settingsModule
import com.spoonofcode.poa.core.ui.di.uiModule
import com.spoonofcode.poa.feature.appsettings.di.appSettingsModule
import com.spoonofcode.poa.feature.demo.di.demoModule
import com.spoonofcode.poa.feature.home.di.homeModule
import com.spoonofcode.poa.feature.login.di.loginModule
import com.spoonofcode.poa.feature.notification.di.notificationModule
import com.spoonofcode.poa.feature.partner.di.partnerModule
import com.spoonofcode.poa.feature.product.di.productModule
import com.spoonofcode.poa.feature.profile.di.profileModule
import com.spoonofcode.poa.feature.scan.di.scanModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val appModule = module {
    includes(
        // core
        dataModule,
        domainModule,
        uiModule,
        networkModule,
        settingsModule,

        // features
        appSettingsModule,
        demoModule,
        homeModule,
        loginModule,
        partnerModule,
        profileModule,
        productModule,
        notificationModule,
        scanModule,
    )
    single {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }

            // TODO #34-Try use custom AuthPlugin instead of safeApiCall function
//            install(AuthPlugin) {
//                // Provide a suspend function that does your refresh call
//                refreshTokenFunc = {
//                    getKoin().get<TokenUseCase>().refreshAccessToken()
//                }
//            }
        }
    }
}