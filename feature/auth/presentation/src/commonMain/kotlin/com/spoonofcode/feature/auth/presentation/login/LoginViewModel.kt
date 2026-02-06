package com.spoonofcode.feature.auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.auth.presentation.model.GoogleAccount
import com.spoonofcode.feature.appnavigation.AppModule
import com.spoonofcode.feature.auth.domain.usecase.LoginGoogleUseCase
import com.spoonofcode.feature.auth.domain.usecase.LoginRequestCodeUseCase
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeScreen
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val loginGoogleUseCase: LoginGoogleUseCase,
    private val loginRequestCodeUseCase: LoginRequestCodeUseCase,
) : BaseViewModel<LoginViewState, LoginViewAction>(
    initialViewState = LoginViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: LoginViewAction) {
        when (action) {
            is LoginViewAction.ChangeEmail -> changeEmail(action.email)
            LoginViewAction.SignInWithCode -> signInWithCode()
            is LoginViewAction.SignInWithGoogle -> signInWithGoogle(action.googleAccount)
        }
    }

    private fun changeEmail(email: String) {
        updateState {
            LoginViewState(
                email = email,
                isSignInButtonEnable = isValidEmail(email),
            )
        }
    }

    private fun signInWithCode() {
        viewModelScope.launch {
            showLoadingView()
            loginRequestCodeUseCase(
                email = currentState().email,
            ).onSuccess {
                viewModelNavigator.push(LoginCodeScreen(email = currentState().email))
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }

    private fun signInWithGoogle(googleAccount: GoogleAccount?) {
        println("BARTEK googleAccount = $googleAccount")
        viewModelScope.launch {
            showLoadingView()
            val googleIdToken = requireNotNull(googleAccount?.idToken)
            loginGoogleUseCase(googleIdToken)
                .onSuccess {
                    viewModelNavigator.replaceAll(listOf(
                        routeResolver.resolve(AppModule.MainHostScreen)
                    ))
                }.onFailure {
                    showErrorSnackbar(it)
                }
        }
    }

    fun isValidEmail(email: String): Boolean {
        val email = email.trim()
        return email.isNotEmpty() && email.length <= 254 && EMAIL_REGEX.matches(email)
    }

    companion object {
        private val EMAIL_REGEX = Regex(
            pattern = "^[A-Za-z0-9.!#\$%&'*+/=?^_`{|}~-]+@[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)+$"
        )
    }
}