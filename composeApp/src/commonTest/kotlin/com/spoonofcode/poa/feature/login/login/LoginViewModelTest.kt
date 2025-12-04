package com.spoonofcode.poa.feature.login.login

import app.cash.turbine.test
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.data.repository.LoginRepository
import com.spoonofcode.poa.core.ext.containsTypes
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.login.di.loginTestModule
import com.spoonofcode.poa.feature.login.forgotPassword.ForgotPasswordScreen
import com.spoonofcode.poa.feature.login.register.RegisterScreen
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.matcher.ofType
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
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
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = LoginViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change email`() = runTest {
        val email = "john@doe.com"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginViewAction.ChangeEmail(email))

            assertEquals(
                expected = LoginViewState(
                    email = email,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change password`() = runTest {
        val password = "Secret123"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginViewAction.ChangePassword(password))

            assertEquals(
                expected = LoginViewState(
                    password = password,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `navigates to forgot password screen`() = runTest {
        viewModel.onAction(LoginViewAction.ForgotPassword)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<ForgotPasswordScreen>()) }
    }

    @Test
    fun `navigates to register screen`() = runTest {
        viewModel.onAction(LoginViewAction.SignUp)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<RegisterScreen>()) }
    }

    @Test
    fun `sign in success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginViewAction.SignIn)

            assertEquals(
                expected = LoginViewState(
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            loginRepository.create(any(), any())
        }
        verifySuspend {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }

    @Test
    fun `sign in error`() = runTest {
        everySuspend {
            loginRepository.create(
                any(),
                any()
            )
        } returns Result.failure(Exception("invalid creds"))

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginViewAction.SignIn)

            assertEquals(
                expected = LoginViewState(
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )

            assertEquals(
                expected = LoginViewState(
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            loginRepository.create(any(), any())
        }

        verifySuspend(mode = exactly(0)) {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }

    @Test
    fun `sign in with Google success`() = runTest {
        val token = "google-id-token"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginViewAction.SignInWithGoogle(token))

            assertEquals(
                expected = LoginViewState(
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            loginGoogleRepository.create(any())
        }
        verifySuspend {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }
}