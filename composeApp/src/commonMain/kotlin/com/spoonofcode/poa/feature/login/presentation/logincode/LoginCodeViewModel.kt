package com.spoonofcode.poa.feature.login.presentation.logincode

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.login.domain.usecase.LoginCodeUseCase
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
            )
        }
    }

    private fun signInWithCode() {
        showLoadingView()
        viewModelScope.launch {
            loginCodeUseCase(
                email = currentState().email,
                code = currentState().code,
            ).onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }

    private fun initView(email: String) {
        showContentView {
            copy(
                email = email,
            )
        }
    }
}