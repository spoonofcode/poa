package com.spoonofcode.feature.auth.presentation.logincode

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.AppModule
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.auth.domain.usecase.LoginCodeUseCase
import kotlinx.coroutines.launch

internal class LoginCodeViewModel(
    private val loginCodeUseCase: LoginCodeUseCase,
) : BaseViewModel<LoginCodeViewState, LoginCodeViewAction>(
    LoginCodeViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: LoginCodeViewAction) {
        when (action) {
            is LoginCodeViewAction.ChangeCode -> changeCode(code = action.code)
            is LoginCodeViewAction.SignInWithCode -> signInWithCode()
            is LoginCodeViewAction.InitView -> initView(email = action.email)
        }
    }

    private fun changeCode(code: String) {
        updateState {
            copy(
                code = code,
                isSignInWithCodeButtonEnable = code.length == VERIFICATION_CODE_LENGTH
            )
        }
    }

    private fun signInWithCode() {
        showLoadingView()
        val currentState = currentState()
        viewModelScope.launch {
            loginCodeUseCase(
                email = currentState.email,
                code = currentState.code,
            ).onSuccess {
                viewModelNavigator.replaceAll(
                    listOf(
                        routeResolver.resolve(AppModule.MainHostScreen),
                        routeResolver.resolve(NotificationModule.NotificationSettingsScreen),
                    )
                )
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }

    private fun initView(email: String) {
        showContentView {
            LoginCodeViewState(
                email = email,
            )
        }
    }

    companion object {
        private const val VERIFICATION_CODE_LENGTH = 6
    }
}