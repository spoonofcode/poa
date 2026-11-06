package com.spoonofcode.feature.auth.presentation.login

import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import com.spoonofcode.feature.auth.domain.repository.LoginRepository
import com.spoonofcode.feature.auth.presentation.di.authPresentationTestModule
import com.spoonofcode.feature.auth.presentation.model.GoogleAccount
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import com.spoonofcode.core.presentation.Res as CoreRes

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
    fun `initial view`() =
        runTest {
            runVmTest(viewModel, expectMostRecentItem = false) {
                with(viewState.awaitContentState()) {
                    assertEquals("", email)
                    assertFalse(isSignInButtonLoading)
                    assertFalse(isSignInGoogleButtonLoading)
                }
            }
        }

    @Test
    fun `change email`() =
        runTest {
            val email = "test@example.com"
            runVmTest(viewModel) {
                onAction(LoginViewAction.ChangeEmail(email))

                with(viewState.awaitContentState()) {
                    assertEquals(email, this.email)
                }
            }
        }

    @Test
    fun `sign in with Google success`() =
        runTest {
            val token = "google-id-token"
            runVmTest(viewModel) {
                onAction(LoginViewAction.SignInWithGoogle(GoogleAccount(idToken = token)))

                with(viewState.awaitContentState()) {
                    assertTrue(isSignInGoogleButtonLoading)
                }

                verifySuspend { loginRepository.loginWithGoogle(token) }
                verifySuspend {
                    viewModelNavigator.replaceAll(
                        listOf(
                            HomeModuleRoute.Home,
                            NotificationModuleRoute.NotificationSettings(backNavigationEnabled = false),
                            TACModuleRoute.TAC(backNavigationEnabled = false),
                        ),
                    )
                }
            }
        }

    @Test
    fun `sign in with Google error`() =
        runTest {
            everySuspend { loginRepository.loginWithGoogle(any()) } returns
                Result.failure(
                    Exception("Google login error"),
                )

            val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)

            runVmTest(viewModel) {
                onAction(LoginViewAction.SignInWithGoogle(GoogleAccount(idToken = "token")))

                with(viewState.awaitContentState()) {
                    assertTrue(isSignInGoogleButtonLoading)
                }
                with(viewState.awaitContentState()) {
                    assertFalse(isSignInGoogleButtonLoading)
                }
                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
                )

                verifySuspend { loginRepository.loginWithGoogle(any()) }
            }
        }

    @Test
    fun `sign in with code success`() =
        runTest {
            val email = "test@example.com"
            runVmTest(viewModel) {
                onAction(LoginViewAction.ChangeEmail(email))
                onAction(LoginViewAction.SignInWithCode)

                with(viewState.awaitContentState()) {
                    assertEquals(email, this.email)
                }
                with(viewState.awaitContentState()) {
                    assertTrue(isSignInButtonLoading)
                }

                verifySuspend { loginRepository.requestCode(email = email, platform = any(), recaptchaToken = any()) }
                verifySuspend {
                    viewModelNavigator.push(AuthModuleRoute.LoginCode(email = email))
                }
            }
        }

    @Test
    fun `sign in with code error`() =
        runTest {
            everySuspend { loginRepository.requestCode(any(), any(), any()) } returns
                Result.failure(
                    Exception("Request code error"),
                )

            val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)
            val email = "test@example.com"

            runVmTest(viewModel) {
                onAction(LoginViewAction.ChangeEmail(email))
                onAction(LoginViewAction.SignInWithCode)

                with(viewState.awaitContentState()) {
                    assertEquals(email, this.email)
                }
                with(viewState.awaitContentState()) {
                    assertTrue(isSignInButtonLoading)
                }
                with(viewState.awaitContentState()) {
                    assertFalse(isSignInButtonLoading)
                }
                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
                )

                verifySuspend { loginRepository.requestCode(any(), any(), any()) }
            }
        }
}
