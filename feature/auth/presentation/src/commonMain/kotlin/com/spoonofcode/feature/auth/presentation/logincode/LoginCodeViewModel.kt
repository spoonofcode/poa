package com.spoonofcode.feature.auth.presentation.logincode

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import com.spoonofcode.feature.auth.domain.usecase.LoginCodeUseCase
import kotlinx.coroutines.launch

internal class LoginCodeViewModel(
    private val loginCodeUseCase: LoginCodeUseCase,
) : BaseViewModel<LoginCodeViewState, LoginCodeViewAction, Nothing>(
    initialViewState = ViewState.Content(LoginCodeViewState()),
) {

    override fun onAction(action: LoginCodeViewAction) {
        when (action) {
            is LoginCodeViewAction.ChangeCode -> changeCode(code = action.code)
            is LoginCodeViewAction.SignInWithCode -> signInWithCode()
            is LoginCodeViewAction.InitView -> initView(email = action.email)
        }
    }

    private fun changeCode(code: String) {
        updateContentState {
            copy(
                code = code,
                isSignInWithCodeButtonEnable = code.length == VERIFICATION_CODE_LENGTH
            )
        }
    }

    private fun signInWithCode() {
        showLoadingView()
        val currentState = currentContentState()
        viewModelScope.launch {
            loginCodeUseCase(
                email = currentState.email,
                code = currentState.code,
            ).onSuccess {
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
                it.message?.let { message -> showErrorSnackbar(message) }
            }
        }
    }

    private fun initView(email: String) {
        setContentState(
            LoginCodeViewState(
                email = email,
            )
        )
    }

    companion object {
        private const val VERIFICATION_CODE_LENGTH = 6
    }
}