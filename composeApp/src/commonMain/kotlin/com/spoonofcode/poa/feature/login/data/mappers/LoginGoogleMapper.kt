package com.spoonofcode.poa.feature.login.data.mappers

import com.spoonofcode.poa.feature.login.data.remote.model.LoginGoogleResponse
import com.spoonofcode.poa.feature.login.domain.model.LoginGoogle


fun LoginGoogleResponse.toLoginGoogle(): LoginGoogle {
    return LoginGoogle(
        userId = userId,
        jwtAccessToken = jwtAccessToken,
        jwtRefreshToken = jwtRefreshToken
    )
}