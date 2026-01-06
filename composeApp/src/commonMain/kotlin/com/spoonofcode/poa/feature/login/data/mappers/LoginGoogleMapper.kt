package com.spoonofcode.poa.feature.login.data.mappers

import com.spoonofcode.poa.feature.login.data.remote.model.LoginGoogleResponse
import com.spoonofcode.poa.feature.login.domain.model.Login

fun LoginGoogleResponse.toLogin(): Login {
    return Login(
        jwtAccessToken = jwtAccessToken,
        jwtRefreshToken = jwtRefreshToken
    )
}