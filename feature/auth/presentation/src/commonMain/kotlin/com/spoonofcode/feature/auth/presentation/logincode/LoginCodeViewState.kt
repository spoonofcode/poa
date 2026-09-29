package com.spoonofcode.feature.auth.presentation.logincode

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class LoginCodeViewState(
    val email: String,
    val code: String = "",
) : BaseViewState() {
    val isSignInWithCodeButtonEnable: Boolean
        get() = code.length == VERIFICATION_CODE_LENGTH

    companion object {
        private const val VERIFICATION_CODE_LENGTH = 6
    }
}