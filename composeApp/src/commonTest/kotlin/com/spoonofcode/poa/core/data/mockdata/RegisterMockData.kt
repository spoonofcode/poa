package com.spoonofcode.poa.core.data.mockdata

import com.spoonofcode.poa.feature.login.domain.model.Register

object RegisterMockData {
    val REGISTER_1 = Register(
        userId = "1",
        jwtAccessToken = "jwtAccessToken 1",
        jwtRefreshToken = "jwtRefreshToken 1",
    )
}