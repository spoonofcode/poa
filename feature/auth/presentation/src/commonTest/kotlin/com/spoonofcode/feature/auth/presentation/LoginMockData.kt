package com.spoonofcode.feature.auth.presentation

import com.spoonofcode.feature.auth.domain.model.Login

object LoginMockData {
    val LOGIN_1 = Login(
        jwtAccessToken = "jwtAccessToken 1",
        jwtRefreshToken = "jwtRefreshToken 1",
    )
}