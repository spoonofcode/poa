package com.spoonofcode.poa.feature.login.login

sealed interface LoginViewAction {
    data class ChangeEmail(val email: String) : LoginViewAction
    data class ChangePassword(val password: String) : LoginViewAction
    data object ForgotPassword : LoginViewAction
    data object SignIn : LoginViewAction
    data class SignInWithGoogle(val googleIdToken: String) : LoginViewAction
    data object SignUp : LoginViewAction
}