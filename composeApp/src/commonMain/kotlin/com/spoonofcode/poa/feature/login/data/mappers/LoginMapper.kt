package com.spoonofcode.poa.feature.login.data.mappers

import com.spoonofcode.poa.feature.login.data.remote.model.LoginResponse
import com.spoonofcode.poa.feature.login.domain.model.Login

fun LoginResponse.toLogin(): Login {
    return Login(
        jwtAccessToken = jwtAccessToken,
        jwtRefreshToken = jwtRefreshToken
    )
}