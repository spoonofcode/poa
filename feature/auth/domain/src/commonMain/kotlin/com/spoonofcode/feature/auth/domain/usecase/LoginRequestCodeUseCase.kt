package com.spoonofcode.feature.auth.domain.usecase

import com.spoonofcode.core.data.Platform
import com.spoonofcode.core.data.PlatformType
import com.spoonofcode.core.recaptcha.RecaptchaClient
import com.spoonofcode.feature.auth.domain.repository.LoginRepository

private const val RECAPTCHA_LOGIN_ACTION = "login"

class LoginRequestCodeUseCase(
    private val loginRepository: LoginRepository,
    private val client: RecaptchaClient,
    private val platform: Platform,
) {
    suspend operator fun invoke(
        email: String,
    ): Result<Unit> {
        val recaptchaToken = client.execute(action = RECAPTCHA_LOGIN_ACTION)
        val platformString = when (platform.platformType) {
            PlatformType.Android -> "android"
            PlatformType.iOS -> "web"
        }
        return loginRepository.requestCode(
            email = email,
            platform = platformString,
            recaptchaToken = recaptchaToken
        )
    }
}
