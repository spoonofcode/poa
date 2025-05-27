package com.spoonofcode.poa.feature.login.login

import app.cash.turbine.test
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.data.repository.LoginRepository
import com.spoonofcode.poa.core.model.LoginRequest
import com.spoonofcode.poa.feature.login.di.loginTestModule
import com.spoonofcode.poa.feature.login.forgotPassword.ForgotPasswordScreen
import com.spoonofcode.poa.feature.login.register.RegisterScreen
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.matcher.eq
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginRepository: LoginRepository
    private lateinit var loginGoogleRepository: LoginGoogleRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(loginTestModule)
        super.setup()
        loginRepository = getKoin().get()
        loginGoogleRepository = getKoin().get()
    }

    @Test
    fun `change email updates state`() = runTest {
        viewModel = getSut()
        val email = "john@doe.com"

        viewModel.changeEmail(email)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(email, awaitItem().email)
        }
    }

    @Test
    fun `change password updates state`() = runTest {
        viewModel = getSut()
        val password = "Secret123"

        viewModel.changePassword(password)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(password, awaitItem().password)
        }
    }

    @Test
    fun `forgot password navigates to screen`() = runTest {
        viewModel = getSut()

        viewModel.forgotPassword()
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<ForgotPasswordScreen>()) }
    }

    @Test
    fun `sign up navigates to register screen`() = runTest {
        viewModel = getSut()

        viewModel.signUp()
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<RegisterScreen>()) }
    }

    // TODO #78 - Fix and add missing Unit tests
    @Ignore
    @Test
    fun `sign in success calls usecase and replaces stack`() = runTest {
        val email = "john@doe.com"
        val password = "Secret123"

        viewModel = getSut()
        viewModel.changeEmail(email)
        viewModel.changePassword(password)
        advanceUntilIdle()

        viewModel.signIn()
        advanceUntilIdle()

        verifySuspend {
            loginRepository.create(
                request = LoginRequest(
                    email = email,
                    password = password,
                )
            )
        }
        verifySuspend { viewModelNavigator.replaceAll(eq(listOf(MainHostScreen()))) }
    }

    @Test
    fun `sign in error hides loader`() = runTest {
        everySuspend { loginRepository.create(any()) } throws Exception("invalid creds")

        viewModel = getSut()
        viewModel.changeEmail("a@b.c")
        viewModel.changePassword("x")
        advanceUntilIdle()

        viewModel.signIn()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(false, awaitItem().isLoadingView)
        }
    }

    // TODO #78 - Fix and add missing Unit tests
    @Ignore()
    @Test
    fun `sign in with Google success`() = runTest {
        val token = "google-id-token"

        viewModel = getSut()
        viewModel.signInWithGoogle(token)
        advanceUntilIdle()

        verifySuspend { loginGoogleRepository.create(any()) }
        verifySuspend { viewModelNavigator.replaceAll(eq(listOf(MainHostScreen()))) }
    }

    @Test
    fun `sign in with Google error hides loader`() = runTest {
        everySuspend { loginGoogleRepository.create(any()) } throws Exception("oauth failed")

        viewModel = getSut()
        viewModel.signInWithGoogle("broken")
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(false, awaitItem().isLoadingView)
        }
    }
}