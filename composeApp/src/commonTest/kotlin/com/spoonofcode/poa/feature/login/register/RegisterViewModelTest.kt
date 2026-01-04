package com.spoonofcode.poa.feature.login.register

import app.cash.turbine.test
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.login.data.remote.RegisterDataSource
import com.spoonofcode.poa.core.ext.containsTypes
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.login.di.loginTestModule
import com.spoonofcode.poa.feature.login.presentation.register.RegisterViewAction
import com.spoonofcode.poa.feature.login.presentation.register.RegisterViewModel
import com.spoonofcode.poa.feature.login.presentation.register.RegisterViewState
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var registerDataSource: RegisterDataSource

    @BeforeTest
    override fun setup() {
        modules = arrayOf(loginTestModule)
        super.setup()
        registerDataSource = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = RegisterViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change email`() = runTest {
        val email = "john@doe.com"

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(RegisterViewAction.ChangeEmail(email))

            assertEquals(email, awaitItem().email)
        }
    }

    @Test
    fun `change first name`() = runTest {
        val firstName = "John"

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(RegisterViewAction.ChangeFirstName(firstName))

            assertEquals(firstName, awaitItem().firstName)
        }
    }

    @Test
    fun `change last name`() = runTest {
        val lastName = "Smith"

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(RegisterViewAction.ChangeLastName(lastName))

            assertEquals(lastName, awaitItem().lastName)
        }
    }

    @Test
    fun `change password`() = runTest {
        val password = "Secret123"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(RegisterViewAction.ChangePassword(password))

            assertEquals(password, awaitItem().password)
        }
    }


    @Test
    fun `sign up success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(RegisterViewAction.SignUp)

            assertEquals(
                expected = RegisterViewState(
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            registerDataSource.create(any(), any(), any(), any())
        }
        verifySuspend {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }

    @Test
    fun `sign up error`() = runTest {
        everySuspend {
            registerDataSource.create(
                any(),
                any(),
                any(),
                any()
            )
        } throws Exception("Something wrong")

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(RegisterViewAction.SignUp)

            assertEquals(
                expected = RegisterViewState(
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )

            assertEquals(
                expected = RegisterViewState(
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            registerDataSource.create(any(), any(), any(), any())
        }

        verifySuspend(mode = exactly(0)) {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }
}