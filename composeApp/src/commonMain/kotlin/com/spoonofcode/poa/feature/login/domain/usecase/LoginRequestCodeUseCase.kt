package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.core.recaptcha.RecaptchaClient
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository

class LoginRequestCodeUseCase(
    private val loginRepository: LoginRepository,
    private val sessionManager: SessionManager,
    private val client: RecaptchaClient,
) {
    suspend operator fun invoke(
        email: String,
    ): Result<Unit> = loginRepository.requestCode(
        email = email,
        recaptchaToken = client.execute(action = "login")
    ).map { loginResponse ->
//        sessionManager.saveSessionUserId(userId = loginResponse.userId)
//        sessionManager.saveSessionAccessToken(token = loginResponse.jwtAccessToken)
//        sessionManager.saveSessionRefreshToken(token = loginResponse.jwtRefreshToken)
//        sessionManager.enableAllNotifications()
    }
}