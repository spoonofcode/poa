package com.spoonofcode.poa.feature.login.register

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class RegisterViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
) : BaseViewState(screenState)