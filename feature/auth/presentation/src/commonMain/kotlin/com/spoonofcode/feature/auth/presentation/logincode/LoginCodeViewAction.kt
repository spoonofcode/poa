package com.spoonofcode.feature.auth.presentation.logincode

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface LoginCodeViewAction : BaseViewAction {
    data class ChangeCode(
        val code: String,
    ) : LoginCodeViewAction

    data object SignInWithCode : LoginCodeViewAction
}
