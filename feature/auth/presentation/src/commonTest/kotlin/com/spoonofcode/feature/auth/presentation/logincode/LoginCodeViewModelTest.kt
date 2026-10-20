package com.spoonofcode.feature.auth.presentation.logincode

import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import com.spoonofcode.feature.auth.domain.repository.LoginRepository
import com.spoonofcode.feature.auth.presentation.di.authPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import org.koin.core.parameter.parametersOf
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import com.spoonofcode.core.presentation.Res as CoreRes

class LoginCodeViewModelTest : BaseViewModelTest() {

    private val navigationParamEmail = "test@example.com"

    private lateinit var viewModel: LoginCodeViewModel
    private lateinit var loginRepository: LoginRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(authPresentationTestModule)
        super.beforeTest()
        loginRepository = getKoin().get()
        viewModel = getKoin().get { parametersOf(navigationParamEmail) }
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial view`() = runTest {
        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            with(viewState.awaitContentState()) {
                assertEquals(navigationParamEmail, email)
                assertEquals("", code)
                assertFalse(isSignInWithCodeButtonLoading)
            }
        }
    }

    @Test
    fun `change code`() = runTest {
        val code = "123456"
        runVmTest(viewModel) {
            onAction(LoginCodeViewAction.ChangeCode(code))

            with(viewState.awaitContentState()) {
                assertEquals(code, this.code)
            }
        }
    }

    @Test
    fun `sign in with code success`() = runTest {
        val code = "123456"
        runVmTest(viewModel) {
            onAction(LoginCodeViewAction.ChangeCode(code))
            onAction(LoginCodeViewAction.SignInWithCode)

            with(viewState.awaitContentState()) {
                assertEquals(code, this.code)
            }
            with(viewState.awaitContentState()) {
                assertTrue(isSignInWithCodeButtonLoading)
            }

            verifySuspend { loginRepository.loginWithCode(email = navigationParamEmail, loginCode = code) }
            verifySuspend {
                viewModelNavigator.replaceAll(
                    listOf(
                        HomeModuleRoute.Home,
                        NotificationModuleRoute.NotificationSettings(backNavigationEnabled = false),
                        TACModuleRoute.TAC(backNavigationEnabled = false),
                    )
                )
            }
        }
    }

    @Test
    fun `sign in with code error`() = runTest {
        everySuspend { loginRepository.loginWithCode(any(), any()) } returns Result.failure(
            Exception("Login code error")
        )

        val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)
        val code = "123456"

        runVmTest(viewModel) {
            onAction(LoginCodeViewAction.ChangeCode(code))
            onAction(LoginCodeViewAction.SignInWithCode)

            with(viewState.awaitContentState()) {
                assertEquals(code, this.code)
            }
            with(viewState.awaitContentState()) {
                assertTrue(isSignInWithCodeButtonLoading)
            }
            with(viewState.awaitContentState()) {
                assertFalse(isSignInWithCodeButtonLoading)
            }
            assertEquals(
                expected = expectedErrorMessage,
                actual = snackbarEvent.awaitItem().message
            )

            verifySuspend { loginRepository.loginWithCode(any(), any()) }
        }
    }
}
