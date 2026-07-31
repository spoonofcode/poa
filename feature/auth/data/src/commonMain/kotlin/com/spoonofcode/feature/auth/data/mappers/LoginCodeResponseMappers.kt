package com.spoonofcode.feature.auth.data.mappers

import com.spoonofcode.feature.auth.data.remote.model.LoginCodeResponse
import com.spoonofcode.feature.auth.domain.model.Login

fun LoginCodeResponse.toLogin(): Login {
    return Login(
        accessToken = accessToken,
        refreshToken = refreshToken,
        id = userProfile.id,
        email = userProfile.email,
    )
}