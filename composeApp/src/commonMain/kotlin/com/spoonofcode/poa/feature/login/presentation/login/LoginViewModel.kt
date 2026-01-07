package com.spoonofcode.poa.feature.login.presentation.login

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.feature.login.domain.usecase.LoginGoogleUseCase
import com.spoonofcode.poa.feature.login.domain.usecase.LoginUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.login.domain.usecase.LoginRequestCodeUseCase
import com.spoonofcode.poa.feature.login.presentation.forgotPassword.ForgotPasswordScreen
import com.spoonofcode.poa.feature.login.presentation.logincode.LoginCodeScreen
import com.spoonofcode.poa.feature.login.presentation.register.RegisterScreen
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val loginGoogleUseCase: LoginGoogleUseCase,
    private val loginRequestCodeUseCase: LoginRequestCodeUseCase,
) : BaseViewModel<LoginViewState, LoginViewAction>(LoginViewState()) {

    override fun onAction(action: LoginViewAction) {
        when (action) {
            is LoginViewAction.ChangeEmail -> changeEmail(action.email)
            is LoginViewAction.ChangePassword -> changePassword(action.password)
            LoginViewAction.ForgotPassword -> forgotPassword()
            LoginViewAction.SignIn -> signIn()
            LoginViewAction.SignInWithCode -> signInWithCode()
            is LoginViewAction.SignInWithGoogle -> signInWithGoogle(action.googleIdToken)
            LoginViewAction.SignUp -> signUp()
        }
    }

    private fun changeEmail(email: String) {
        updateState {
            copy(email = email)
        }
    }

    private fun changePassword(password: String) {
        updateState {
            copy(password = password)
        }
    }

    private fun forgotPassword() {
        viewModelScope.launch {
            viewModelNavigator.push(ForgotPasswordScreen())
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            showLoadingView()
            loginUseCase(
                email = viewState.value.email,
                password = viewState.value.password,
            ).onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }

    private fun signInWithCode() {
        viewModelScope.launch {
            showLoadingView()
            loginRequestCodeUseCase(
                email = viewState.value.email,
            ).onSuccess {
                viewModelNavigator.push(LoginCodeScreen(email = viewState.value.email))
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }

    private fun signInWithGoogle(googleIdToken: String) {
        viewModelScope.launch {
            showLoadingView()
            loginGoogleUseCase(googleIdToken)
                .onSuccess {
                    viewModelNavigator.replaceAll(listOf(MainHostScreen()))
                }.onFailure {
                    showErrorSnackbar(it)
                }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            viewModelNavigator.push(RegisterScreen())
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorSnackbar(e: Throwable) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                screenState = ScreenState.CONTENT
            )
        }
    }
}