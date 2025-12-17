package com.spoonofcode.poa.feature.login.presentation.forgotPassword

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface ForgotPasswordViewAction: BaseViewAction {
    data class ChangeEmail(val email: String) : ForgotPasswordViewAction
    data object ResetPassword : ForgotPasswordViewAction
}