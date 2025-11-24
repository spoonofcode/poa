package com.spoonofcode.poa.feature.login.forgotPassword

sealed interface ForgotPasswordViewAction {
    data class ChangeEmail(val email: String) : ForgotPasswordViewAction
    data object ResetPassword : ForgotPasswordViewAction
}