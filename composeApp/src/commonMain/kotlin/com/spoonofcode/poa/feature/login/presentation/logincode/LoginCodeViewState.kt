package com.spoonofcode.poa.feature.login.presentation.logincode

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class LoginCodeViewState(
    val email: String = "",
    val code: String = "",
    val isSignInWithCodeButtonEnable: Boolean = false,
) : BaseViewState()