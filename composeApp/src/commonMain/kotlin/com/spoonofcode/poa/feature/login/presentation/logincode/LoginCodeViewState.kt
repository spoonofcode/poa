package com.spoonofcode.poa.feature.login.presentation.logincode

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.base.BaseViewState

@Immutable
internal data class LoginCodeViewState(
    val email: String = "",
    val code: String = "",
): BaseViewState()