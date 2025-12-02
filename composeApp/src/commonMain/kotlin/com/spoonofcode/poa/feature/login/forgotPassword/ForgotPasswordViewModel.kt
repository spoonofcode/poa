package com.spoonofcode.poa.feature.login.forgotPassword

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.core.ui.BaseViewModel
import kotlinx.coroutines.launch

internal class ForgotPasswordViewModel(
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionManager: SessionManager,
) : BaseViewModel<ForgotPasswordViewState, ForgotPasswordViewAction>(ForgotPasswordViewState()) {

    override fun onAction(action: ForgotPasswordViewAction) {
        when (action) {
            is ForgotPasswordViewAction.ChangeEmail -> changeEmail(email = action.email)
            ForgotPasswordViewAction.ResetPassword -> resetPassword()
        }
    }

    private fun changeEmail(email: String) {
            updateState {
                copy(
                    email = email,
                )
            }
    }

    private fun resetPassword() {
        viewModelScope.launch {

        }
    }
}