package com.spoonofcode.poa.feature.login.data.mappers

import com.spoonofcode.poa.feature.login.data.remote.model.RegisterResponse
import com.spoonofcode.poa.feature.login.domain.model.Register

fun RegisterResponse.toRegister(): Register {
    return Register(
        userId = userId,
        jwtAccessToken = jwtAccessToken,
        jwtRefreshToken = jwtRefreshToken
    )
}