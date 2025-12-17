package com.spoonofcode.poa.feature.login.login

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.domain.login.LoginGoogleUseCase
import com.spoonofcode.poa.core.domain.login.LoginUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.login.forgotPassword.ForgotPasswordScreen
import com.spoonofcode.poa.feature.login.register.RegisterScreen
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val loginGoogleUseCase: LoginGoogleUseCase,
) : BaseViewModel<LoginViewState, LoginViewAction>(LoginViewState()) {

    override fun onAction(action: LoginViewAction) {
        when (action) {
            is LoginViewAction.ChangeEmail -> changeEmail(action.email)
            is LoginViewAction.ChangePassword -> changePassword(action.password)
            LoginViewAction.ForgotPassword -> forgotPassword()
            LoginViewAction.SignIn -> signIn()
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