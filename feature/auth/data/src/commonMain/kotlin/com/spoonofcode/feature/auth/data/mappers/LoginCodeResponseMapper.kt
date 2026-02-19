package com.spoonofcode.feature.auth.data.mappers

import com.spoonofcode.feature.auth.data.remote.model.LoginCodeResponse
import com.spoonofcode.feature.auth.domain.model.Login

fun LoginCodeResponse.toLogin(): Login {
    return Login(
        jwtAccessToken = jwtAccessToken,
        jwtRefreshToken = jwtRefreshToken,
        email = userProfile.email,
    )
}