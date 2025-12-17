package com.spoonofcode.poa.feature.login.presentation.register

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class RegisterViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
) : BaseViewState(screenState)