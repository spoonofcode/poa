package com.spoonofcode.feature.auth.data.mappers

import com.spoonofcode.feature.auth.data.remote.model.LoginResponse
import com.spoonofcode.feature.login.domain.model.Login

fun LoginResponse.toLogin(): Login {
    return Login(
        jwtAccessToken = jwtAccessToken,
        jwtRefreshToken = jwtRefreshToken
    )
}