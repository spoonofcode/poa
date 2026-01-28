package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.core.data.recaptcha.RecaptchaClient
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository

private const val RECAPTCHA_LOGIN_ACTION = "login"
private const val RECAPTCHA_SIGN_UP_ACTION = "signup"

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