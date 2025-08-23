package com.spoonofcode.poa.feature.login.login

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class LoginViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val email: String = "bartosz.luczak@gmail.com",
    val password: String = "bartosz123",
) : BaseViewState(screenState)