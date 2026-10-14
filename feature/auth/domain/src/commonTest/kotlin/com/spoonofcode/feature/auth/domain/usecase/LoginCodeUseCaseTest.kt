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

class LoginCodeUseCaseTest : BaseTest() {

    private lateinit var loginCodeUseCase: LoginCodeUseCase
    private lateinit var loginRepository: LoginRepository
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(authDomainTestModule)
        super.beforeTest()
        loginRepository = getKoin().get()
        sessionRepository = getKoin().get()
        loginCodeUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success and initializes session`() = runTest {
        val email = "test@example.com"
        val code = "123456"

        val result = loginCodeUseCase(email, code)

        assertTrue(result.isSuccess)
        verifySuspend { loginRepository.loginWithCode(email, code) }
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
        val email = "test@example.com"
        val code = "123456"
        val exception = Exception("Invalid code")
        everySuspend { loginRepository.loginWithCode(email, code) } returns Result.failure(exception)

        val result = loginCodeUseCase(email, code)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { loginRepository.loginWithCode(email, code) }
    }
}
