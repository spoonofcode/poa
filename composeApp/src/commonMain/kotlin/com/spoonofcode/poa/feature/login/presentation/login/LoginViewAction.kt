package com.spoonofcode.poa.feature.login.presentation.login

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface LoginViewAction: BaseViewAction {
    data class ChangeEmail(val email: String) : LoginViewAction
    data class ChangePassword(val password: String) : LoginViewAction
    data object ForgotPassword : LoginViewAction
    data object SignIn : LoginViewAction
    data class SignInWithGoogle(val googleIdToken: String) : LoginViewAction
    data object SignUp : LoginViewAction
}