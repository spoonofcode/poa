package com.spoonofcode.feature.auth.presentation.login

import com.spoonofcode.core.presentation.base.BaseViewAction
import com.spoonofcode.feature.auth.presentation.model.GoogleAccount

internal sealed interface LoginViewAction : BaseViewAction {
    data class ChangeEmail(val email: String) : LoginViewAction
    data object SignInWithCode : LoginViewAction
    data class SignInWithGoogle(val googleAccount: GoogleAccount?) : LoginViewAction
}