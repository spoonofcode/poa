package com.spoonofcode.feature.auth.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.auth.data.test.LoginMockData
import com.spoonofcode.feature.auth.domain.di.authDomainTestModule
import com.spoonofcode.feature.auth.domain.repository.LoginRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoginGoogleUseCaseTest : BaseTest() {

    private lateinit var loginGoogleUseCase: LoginGoogleUseCase
    private lateinit var loginRepository: LoginRepository
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(authDomainTestModule)
        super.beforeTest()
        loginRepository = getKoin().get()
        sessionRepository = getKoin().get()
        loginGoogleUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success and initializes session`() = runTest {
        val token = "google_token"

        val result = loginGoogleUseCase(token)

        assertTrue(result.isSuccess)
        verifySuspend { loginRepository.loginWithGoogle(token) }
        verifySuspend {
            sessionRepository.initSession(
                accessToken = LoginMockData.LOGIN_1.accessToken,
                refreshToken = LoginMockData.LOGIN_1.refreshToken,
                userId = LoginMockData.LOGIN_1.id,
                userEmail = LoginMockData.LOGIN_1.email,
            )
        }
    }

    @Test
    fun `invoke returns failure when repository throws`() = runTest {
        val token = "google_token"
        val exception = Exception("Google login failed")
        everySuspend { loginRepository.loginWithGoogle(token) } returns Result.failure(exception)

        val result = loginGoogleUseCase(token)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { loginRepository.loginWithGoogle(token) }
    }
}
