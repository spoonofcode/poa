package com.spoonofcode.poa.feature.login.domain.repository

import com.spoonofcode.poa.feature.login.domain.model.Login
import com.spoonofcode.poa.feature.login.domain.model.LoginGoogle
import com.spoonofcode.poa.feature.login.domain.model.Register

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<Login>

    suspend fun loginWithCode(email: String, loginCode: String): Result<Login>

    suspend fun loginWithGoogle(googleUserToken: String): Result<LoginGoogle>

    suspend fun requestCode(email: String, recaptchaToken: String): Result<Unit>

    suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Result<Register>
}