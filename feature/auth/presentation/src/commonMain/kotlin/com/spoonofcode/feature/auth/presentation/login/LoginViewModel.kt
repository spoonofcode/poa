package com.spoonofcode.feature.auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import com.spoonofcode.feature.auth.domain.usecase.LoginGoogleUseCase
import com.spoonofcode.feature.auth.domain.usecase.LoginRequestCodeUseCase
import com.spoonofcode.feature.auth.presentation.model.GoogleAccount
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val loginGoogleUseCase: LoginGoogleUseCase,
    private val loginRequestCodeUseCase: LoginRequestCodeUseCase,
) : BaseViewModel<LoginViewState, LoginViewAction, Nothing>(
    initialViewState = ViewState.Content(LoginViewState()),
) {

    override fun onAction(action: LoginViewAction) {
        when (action) {
            is LoginViewAction.ChangeEmail -> changeEmail(action.email)
            LoginViewAction.SignInWithCode -> signInWithCode()
            is LoginViewAction.SignInWithGoogle -> signInWithGoogle(action.googleAccount)
        }
    }

    private fun changeEmail(email: String) {
        updateContentState {
            LoginViewState(
                email = email,
            )
        }
    }

    private fun signInWithCode() {
        updateContentState { copy(isSignInButtonLoading = true) }
        viewModelScope.launch {
            loginRequestCodeUseCase(
                email = currentContentState().email,
            ).onSuccess {
                viewModelNavigator.push(AuthModuleRoute.LoginCode(email = currentContentState().email))
            }.onFailure {
                updateContentState {
                    copy(
                        isSignInButtonLoading = false,
                    )
                }
                it.message?.let { message -> showErrorSnackbar(message) }
            }
        }
    }

    private fun signInWithGoogle(googleAccount: GoogleAccount?) {
        updateContentState { copy(isSignInGoogleButtonLoading = true) }
        viewModelScope.launch {
            val googleIdToken = requireNotNull(googleAccount?.idToken)
            loginGoogleUseCase(googleIdToken)
                .onSuccess {
                    viewModelNavigator.replaceAll(
                        listOf(
                            HomeModuleRoute.Home,
                            NotificationModuleRoute.NotificationSettings(
                                backNavigationEnabled = false
                            ),
                            TACModuleRoute.TAC(
                                backNavigationEnabled = false
                            ),
                        )
                    )
                }.onFailure {
                    updateContentState { copy(isSignInGoogleButtonLoading = false) }
                    it.message?.let { message -> showErrorSnackbar(message) }
                }
        }
    }


}