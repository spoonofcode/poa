package com.spoonofcode.feature.auth.presentation.login

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isSignInButtonEnable: Boolean = false,
) : BaseViewState()