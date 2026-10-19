package com.spoonofcode.feature.auth.data.mappers

import com.spoonofcode.feature.auth.data.remote.model.LoginGoogleResponse
import com.spoonofcode.feature.auth.domain.model.Login

internal fun LoginGoogleResponse.toLogin(): Login = Login(
    accessToken = accessToken,
    refreshToken = refreshToken,
    id = userProfile.id,
    email = userProfile.email,
)