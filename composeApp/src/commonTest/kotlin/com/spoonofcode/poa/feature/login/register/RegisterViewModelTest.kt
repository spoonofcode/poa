package com.spoonofcode.poa.feature.login.register

import app.cash.turbine.test
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.RegisterRepository
import com.spoonofcode.poa.core.model.RegisterRequest
import com.spoonofcode.poa.feature.login.di.loginTestModule
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.matcher.eq
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var registerRepository: RegisterRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(loginTestModule)
        super.setup()
        registerRepository = getKoin().get()
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
    fun `change first and last name update state`() = runTest {
        viewModel = getSut()
        val first = "John"
        val last = "Doe"

        viewModel.changeFirstName(first)
        viewModel.changeLastName(last)
        advanceUntilIdle()

        viewModel.viewState.test {
            val s = awaitItem()
            assertEquals(first, s.firstName)
            assertEquals(last, s.lastName)
        }
    }

    // TODO #78 - Fix and add missing Unit tests
    @Ignore
    @Test
    fun `sign up success calls usecase and navigates to main host`() = runTest {
        val email = "john@doe.com"
        val pass = "Secret123"
        val first = "John"
        val last = "Doe"

        viewModel = getSut()

        viewModel.changeEmail(email)
        viewModel.changePassword(pass)
        viewModel.changeFirstName(first)
        viewModel.changeLastName(last)
        advanceUntilIdle()

        viewModel.signUp()
        advanceUntilIdle()

        verifySuspend {
            registerRepository.create(
                request = RegisterRequest(
                    email = email,
                    password = pass,
                    firstName = first,
                    lastName = last,
                )
            )
        }
        verifySuspend { viewModelNavigator.replaceAll(eq(listOf(MainHostScreen()))) }
    }

//    @Test
//    fun `sign up error hides loader`() = runTest {
//        everySuspend { registerRepository.create(any<RegisterRequest>()) } throws Exception("test exception")
//
//        viewModel = getSut()
//        viewModel.changeEmail("a@b.c")
//        viewModel.changePassword("x")
//        advanceUntilIdle()
//
//        viewModel.signUp()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(false, awaitItem().isLoadingView)
//        }
//    }
}