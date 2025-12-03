package com.spoonofcode.poa.feature.login.forgotPassword

import com.spoonofcode.poa.core.ui.BaseViewModel

internal class ForgotPasswordViewModel(
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
        // TODO NOT IMPLEMENTED YET
    }
}