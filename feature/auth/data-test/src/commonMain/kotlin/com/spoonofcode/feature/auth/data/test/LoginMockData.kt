package com.spoonofcode.feature.auth.data.test

import com.spoonofcode.feature.auth.domain.model.Login

object LoginMockData {
    val LOGIN_1 = Login(
        accessToken = "Access Token 1",
        refreshToken = "Refresh Token 1",
        email = "user@email.com",
    )
}