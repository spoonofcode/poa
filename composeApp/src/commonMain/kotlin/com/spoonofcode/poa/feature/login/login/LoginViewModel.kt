package com.spoonofcode.poa.feature.login.login

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.domain.LoginGoogleUseCase
import com.spoonofcode.poa.core.domain.LoginUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.login.forgotPassword.ForgotPasswordScreen
import com.spoonofcode.poa.feature.login.register.RegisterScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val loginGoogleUseCase: LoginGoogleUseCase,
) : BaseViewModel<LoginViewState>(LoginViewState()) {

    fun changeEmail(email: String) {
        viewModelScope.launch {
            updateState {
                copy(email = email)
            }
        }
    }

    fun changePassword(password: String) {
        viewModelScope.launch {
            updateState {
                copy(password = password)
            }
        }
    }

    fun forgotPassword() {
        viewModelScope.launch {
            viewModelNavigator.push(ForgotPasswordScreen())
        }
    }

    fun signIn() {
        viewModelScope.launch {
            showLoadingView()
            try {
                loginUseCase(
                    email = viewState.value.email,
                    password = viewState.value.password,
                )
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    fun signInWithGoogle(googleIdToken: String) {
        viewModelScope.launch {
            showLoadingView()
            try {
                loginGoogleUseCase(googleIdToken)
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            viewModelNavigator.push(RegisterScreen())
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                isLoadingView = false
            )
        }
    }
}