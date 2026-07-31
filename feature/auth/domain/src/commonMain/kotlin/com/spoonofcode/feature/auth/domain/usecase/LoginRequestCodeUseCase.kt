package com.spoonofcode.feature.auth.domain.usecase

import com.spoonofcode.core.recaptcha.RecaptchaClient
import com.spoonofcode.feature.auth.domain.repository.LoginRepository

private const val RECAPTCHA_LOGIN_ACTION = "LOGIN"
private const val RECAPTCHA_SIGN_UP_ACTION = "SIGNUP"

class LoginRequestCodeUseCase(
    private val loginRepository: LoginRepository,
    private val client: RecaptchaClient,
) {
    suspend operator fun invoke(
        email: String,
    ): Result<Unit> {
        val recaptchaToken = client.execute(action = RECAPTCHA_LOGIN_ACTION)
        return loginRepository.requestCode(
            email = email,
            recaptchaToken = recaptchaToken
        )
    }
}