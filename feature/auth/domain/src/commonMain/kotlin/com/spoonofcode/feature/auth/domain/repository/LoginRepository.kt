package com.spoonofcode.feature.auth.domain.repository

import com.spoonofcode.feature.auth.domain.model.Login

interface LoginRepository {
    suspend fun loginWithCode(email: String, loginCode: String): Result<Login>
    suspend fun loginWithGoogle(googleUserToken: String): Result<Login>
    suspend fun requestCode(email: String, recaptchaToken: String): Result<Unit>
}