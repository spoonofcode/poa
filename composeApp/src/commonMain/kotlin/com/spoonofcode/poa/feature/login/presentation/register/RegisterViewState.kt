package com.spoonofcode.poa.feature.login.presentation.register

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class RegisterViewState(
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
) : BaseViewState()