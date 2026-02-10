package com.spoonofcode.feature.auth.presentation.login

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.AppModule
import com.spoonofcode.feature.appnavigation.AuthModule
import com.spoonofcode.feature.auth.domain.repository.LoginRepository
import com.spoonofcode.feature.auth.presentation.di.authPresentationTestModule
import com.spoonofcode.feature.auth.presentation.model.GoogleAccount
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginRepository: LoginRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(authPresentationTestModule)
        super.beforeTest()
        loginRepository = getKoin().get()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = LoginViewState(),
                    screenState = ScreenState.CONTENT,
                ),
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
                expected = ViewState(
                    data = LoginViewState(
                        email = email,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `sign in with Google success`() = runTest {
        val token = "google-id-token"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(
                LoginViewAction.SignInWithGoogle(
                    googleAccount = GoogleAccount(
                        idToken = token,
                    )
                )
            )

            assertEquals(
                expected = ViewState(
                    data = LoginViewState(),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend { loginRepository.loginWithGoogle(any()) }
        verifySuspend { routeResolver.resolve(AppModule.MainHostScreen) }
        verifySuspend { viewModelNavigator.replaceAll(any()) }
    }


    @Test
    fun `sign in with Google error`() = runTest {
        val token = "google-id-token"
        everySuspend {
            loginRepository.loginWithGoogle(any())
        } returns Result.failure(Exception("error"))

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(
                LoginViewAction.SignInWithGoogle(
                    googleAccount = GoogleAccount(
                        idToken = token,
                    )
                )
            )

            assertEquals(
                expected = ViewState(
                    data = LoginViewState(),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )

            assertEquals(
                expected = ViewState(
                    data = LoginViewState(),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            loginRepository.loginWithGoogle(any())
        }

        verifySuspend(mode = exactly(0)) {
            viewModelNavigator.replaceAll(any())
        }
    }

    @Test
    fun `sign in with code success`() = runTest {
        val email = "john@doe.com"
        viewModel.onAction(LoginViewAction.ChangeEmail(email))

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginViewAction.SignInWithCode)

            assertEquals(
                expected = ViewState(
                    data = LoginViewState(email = email),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }
        advanceUntilIdle()

        verifySuspend { loginRepository.requestCode(email = email, recaptchaToken = any()) }
        verifySuspend { routeResolver.resolve(AuthModule.LoginCodeScreen(email)) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `sign in with code error`() = runTest {
        val email = "john@doe.com"
        viewModel.onAction(LoginViewAction.ChangeEmail(email))

        everySuspend {
            loginRepository.requestCode(any(), any())
        } returns Result.failure(Exception("error"))

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginViewAction.SignInWithCode)

            assertEquals(
                expected = ViewState(
                    data = LoginViewState(email = email),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )

            assertEquals(
                expected = ViewState(
                    data = LoginViewState(email = email),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend { loginRepository.requestCode(any(), any()) }
        verifySuspend(mode = exactly(0)) { routeResolver.resolve(AuthModule.LoginCodeScreen(email)) }
        verifySuspend(mode = exactly(0)) { viewModelNavigator.push(any()) }
    }
}
