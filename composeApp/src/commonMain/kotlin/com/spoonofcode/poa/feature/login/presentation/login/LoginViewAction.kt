package com.spoonofcode.poa.feature.login.presentation.login

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface LoginViewAction : BaseViewAction {
    data class ChangeEmail(val email: String) : LoginViewAction
    data object SignInWithCode : LoginViewAction
    data class SignInWithGoogle(val googleIdToken: String) : LoginViewAction
}