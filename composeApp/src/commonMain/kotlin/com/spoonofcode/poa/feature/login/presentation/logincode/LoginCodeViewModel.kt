package com.spoonofcode.poa.feature.login.presentation.logincode

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.login.domain.usecase.LoginCodeUseCase
import kotlinx.coroutines.launch

internal class LoginCodeViewModel(
    private val loginCodeUseCase: LoginCodeUseCase,
) : BaseViewModel<LoginCodeViewState, LoginCodeViewAction>(LoginCodeViewState()) {

    override fun onAction(action: LoginCodeViewAction) {
        when (action) {
            is LoginCodeViewAction.ChangeCode -> changeCode(code = action.code)
            is LoginCodeViewAction.LoginWithCode -> loginWithCode()
            is LoginCodeViewAction.InitView -> initView()
        }
    }

    private fun changeCode(code: String) {
        updateState {
            copy(
                code = code,
            )
        }
    }

    private fun loginWithCode() {
        viewModelScope.launch {
            showLoadingView()
            loginCodeUseCase(
                email = currentState().email,
                code = currentState().code,
            ).onSuccess {
                viewModelNavigator.push(LoginCodeScreen(email = currentState().email))
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }

    private fun initView() {
        updateState {
            copy(
                email = email,
            )
        }
        showContentView()
    }

    private fun showErrorSnackbar(e: Throwable) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        showContentView()
    }
}