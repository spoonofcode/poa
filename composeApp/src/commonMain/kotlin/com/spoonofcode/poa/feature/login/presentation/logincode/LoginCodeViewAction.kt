package com.spoonofcode.poa.feature.login.presentation.logincode

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface LoginCodeViewAction: BaseViewAction {
    data class InitView(val email: String) : LoginCodeViewAction
    data class ChangeCode(val code: String) : LoginCodeViewAction
    data object SignInWithCode : LoginCodeViewAction
}