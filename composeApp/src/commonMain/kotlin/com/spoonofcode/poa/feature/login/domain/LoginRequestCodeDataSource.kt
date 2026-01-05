package com.spoonofcode.poa.feature.login.domain

import com.spoonofcode.poa.feature.login.domain.model.Login

interface LoginRequestCodeDataSource {
    suspend fun create(
        email: String,
        recaptchaToken: String,
    ): Result<Login>
}