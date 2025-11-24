package com.spoonofcode.poa.feature.login.forgotPassword

import com.spoonofcode.poa.core.ui.BaseViewAction

sealed interface ForgotPasswordViewAction: BaseViewAction {
    data class ChangeEmail(val email: String) : ForgotPasswordViewAction
    data object ResetPassword : ForgotPasswordViewAction
}