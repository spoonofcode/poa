package com.spoonofcode.poa.feature.login.presentation.logincode

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class LoginCodeViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val email: String = "",
    val code: String = "",
): BaseViewState()