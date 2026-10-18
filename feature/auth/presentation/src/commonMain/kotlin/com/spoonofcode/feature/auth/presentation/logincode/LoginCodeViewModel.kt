package com.spoonofcode.feature.auth.presentation.logincode

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import com.spoonofcode.feature.auth.domain.usecase.LoginCodeUseCase
import kotlinx.coroutines.launch

internal class LoginCodeViewModel(
    private val email: String,
    private val loginCodeUseCase: LoginCodeUseCase,
) : BaseViewModel<LoginCodeViewState, LoginCodeViewAction, Nothing>() {
    override val startAction = {
        initView(email = email)
    }

    override fun onAction(action: LoginCodeViewAction) {
        when (action) {
            is LoginCodeViewAction.ChangeCode -> changeCode(code = action.code)
            is LoginCodeViewAction.SignInWithCode -> signInWithCode()
        }
    }

    private fun changeCode(code: String) {
        updateContentState { copy(code = code) }
    }

    private fun signInWithCode() {
        updateContentState { copy(isSignInWithCodeButtonLoading = true) }
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
                updateContentState { copy(isSignInWithCodeButtonLoading = false) }
                it.message?.let { message -> showErrorSnackbar(UiText.DynamicString(message)) }
            }
        }
    }

    private fun initView(email: String) {
        setContentState(
            LoginCodeViewState(
                email = email,
            )
        )
        showContentView()
    }
}