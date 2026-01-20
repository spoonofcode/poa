package com.spoonofcode.poa.feature.login.logincode

import app.cash.turbine.test
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.ui.BaseViewModelTest
import com.spoonofcode.poa.core.ext.containsTypes
import com.spoonofcode.poa.core.ui.base.ScreenState
import com.spoonofcode.poa.core.ui.base.ViewState
import com.spoonofcode.poa.feature.login.di.loginTestModule
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository
import com.spoonofcode.poa.feature.login.presentation.logincode.LoginCodeViewAction
import com.spoonofcode.poa.feature.login.presentation.logincode.LoginCodeViewModel
import com.spoonofcode.poa.feature.login.presentation.logincode.LoginCodeViewState
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginCodeViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: LoginCodeViewModel
    private lateinit var loginRepository: LoginRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(loginTestModule)
        super.setup()
        loginRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = LoginCodeViewState(),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        val email = "john@doe.com"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginCodeViewAction.InitView(email = email))

            assertEquals(
                expected = ViewState(
                    data = LoginCodeViewState(email = email),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change code`() = runTest {
        val code = "123456"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginCodeViewAction.ChangeCode(code))

            assertEquals(
                expected = ViewState(
                    data = LoginCodeViewState(
                        code = code,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `sign in with code success`() = runTest {
        val email = "john@doe.com"
        val code = "123456"
        viewModel.onAction(LoginCodeViewAction.InitView(email))
        viewModel.onAction(LoginCodeViewAction.ChangeCode(code))

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginCodeViewAction.SignInWithCode)

            assertEquals(
                expected = ViewState(
                    data = LoginCodeViewState(email = email, code = code),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }
        advanceUntilIdle()

        verifySuspend {
            loginRepository.loginWithCode(email = email, loginCode = any())
        }
        verifySuspend {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }

    @Test
    fun `sign in with code error`() = runTest {
        everySuspend {
            loginRepository.loginWithCode(any(), any())
        } returns Result.failure(Exception("error"))

        val email = "john@doe.com"
        val code = "123456"
        viewModel.onAction(LoginCodeViewAction.InitView(email))
        viewModel.onAction(LoginCodeViewAction.ChangeCode(code))

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(LoginCodeViewAction.SignInWithCode)

            assertEquals(
                expected = ViewState(
                    data = LoginCodeViewState(email = email, code = code),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )

            assertEquals(
                expected = ViewState(
                    data = LoginCodeViewState(email = email, code = code),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
        advanceUntilIdle()

        verifySuspend {
            loginRepository.loginWithCode(any(), any())
        }

        verifySuspend(mode = exactly(0)) {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }
}
