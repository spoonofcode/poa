package com.spoonofcode.poa.feature.login.presentation.login

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.ui.base.BaseViewModel
import com.spoonofcode.poa.core.ui.base.ScreenState
import com.spoonofcode.poa.feature.login.domain.model.GoogleAccount
import com.spoonofcode.poa.feature.login.domain.usecase.LoginGoogleUseCase
import com.spoonofcode.poa.feature.login.domain.usecase.LoginRequestCodeUseCase
import com.spoonofcode.poa.feature.login.presentation.logincode.LoginCodeScreen
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
            copy(
                email = email,
                isSignInButtonEnable = isValidEmail(email),
            )
        }
    }

    private fun signInWithCode() {
        viewModelScope.launch {
            viewModelNavigator.push(LoginCodeScreen(email = currentState().email))
//            showLoadingView()
//            loginRequestCodeUseCase(
//                email = currentState().email,
//            ).onSuccess {
//                viewModelNavigator.push(LoginCodeScreen(email = currentState().email))
//            }.onFailure {
//                showErrorSnackbar(it)
//            }
        }
    }

    private fun signInWithGoogle(googleAccount: GoogleAccount?) {
        viewModelScope.launch {
            showLoadingView()
            val googleIdToken = requireNotNull(googleAccount?.idToken)
            loginGoogleUseCase(googleIdToken)
                .onSuccess {
                    viewModelNavigator.replaceAll(listOf(MainHostScreen()))
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