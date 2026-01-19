package com.spoonofcode.poa.core.network.di

import com.spoonofcode.poa.core.network.NetworkManager
import com.spoonofcode.poa.core.network.NetworkManagerImpl
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.core.network.SessionManagerImpl
import dev.jordond.connectivity.Connectivity
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule: Module = module {
    single {
        Connectivity {
            autoStart = true
        }
    }
    singleOf(::SessionManagerImpl).bind(SessionManager::class)
    singleOf(::NetworkManagerImpl).bind(NetworkManager::class)

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        encodeDefaults = true
                        ignoreUnknownKeys = true
                    }
                )
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