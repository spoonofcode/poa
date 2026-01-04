package com.spoonofcode.poa.feature.login.data.mappers.ktor

import com.spoonofcode.poa.feature.login.data.remote.ktor.model.KtorLoginGoogleResponse
import com.spoonofcode.poa.feature.login.domain.ktor.model.KtorLoginGoogle

fun KtorLoginGoogleResponse.toKtorLoginGoogle(): KtorLoginGoogle {
    return KtorLoginGoogle(
        userId = userId,
        jwtAccessToken = jwtAccessToken,
        jwtRefreshToken = jwtRefreshToken
    )
}