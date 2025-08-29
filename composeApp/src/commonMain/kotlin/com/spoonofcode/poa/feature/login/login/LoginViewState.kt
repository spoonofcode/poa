package com.spoonofcode.poa.feature.login.login

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class LoginViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val email: String = "bartosz.luczak@gmail.com",
    val password: String = "bartosz123",
) : BaseViewState(screenState)