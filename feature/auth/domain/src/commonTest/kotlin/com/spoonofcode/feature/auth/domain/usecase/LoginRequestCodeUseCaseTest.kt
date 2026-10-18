package com.spoonofcode.feature.auth.domain.usecase

import com.spoonofcode.core.data.Platform
import com.spoonofcode.core.recaptcha.RecaptchaClient
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.auth.domain.di.authDomainTestModule
import com.spoonofcode.feature.auth.domain.repository.LoginRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoginRequestCodeUseCaseTest : BaseTest() {

    private lateinit var loginRequestCodeUseCase: LoginRequestCodeUseCase
    private lateinit var loginRepository: LoginRepository
    private lateinit var recaptchaClient: RecaptchaClient
    private lateinit var platform: Platform

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(authDomainTestModule)
        super.beforeTest()
        loginRepository = getKoin().get()
        recaptchaClient = getKoin().get()
        platform = getKoin().get()
        loginRequestCodeUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success when recaptcha and loginRepository succeed`() = runTest {
        val email = "test@example.com"
        val recaptchaToken = "token123"

        everySuspend { recaptchaClient.execute(any()) } returns recaptchaToken
        everySuspend {
            loginRepository.requestCode(
                email = email,
                platform = "android",
                recaptchaToken = recaptchaToken,
            )
        } returns Result.success(Unit)

        val result = loginRequestCodeUseCase(email)

        assertTrue(result.isSuccess)
        verifySuspend {
            loginRepository.requestCode(
                email = email,
                platform = "android",
                recaptchaToken = recaptchaToken,
            )
        }
    }

    @Test
    fun `invoke returns failure when repository throws`() = runTest {
        val email = "test@example.com"
        val recaptchaToken = "token123"
        val exception = Exception("Failed to request code")

        everySuspend { recaptchaClient.execute(any()) } returns recaptchaToken
        everySuspend {
            loginRepository.requestCode(
                email = email,
                platform = "android",
                recaptchaToken = recaptchaToken,
            )
        } returns Result.failure(exception)

        val result = loginRequestCodeUseCase(email)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
