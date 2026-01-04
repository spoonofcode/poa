package com.spoonofcode.poa.core.data.mockdata

import com.spoonofcode.poa.feature.login.domain.model.Login

object LoginMockData {
    val LOGIN_1 = Login(
        userId = "1",
        jwtAccessToken = "jwtAccessToken 1",
        jwtRefreshToken = "jwtRefreshToken 1",
    )
}