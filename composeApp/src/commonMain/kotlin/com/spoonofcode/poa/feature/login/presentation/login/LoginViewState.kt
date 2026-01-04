package com.spoonofcode.poa.feature.login.presentation.login

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class LoginViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val email: String = "daria.waszkiewicz@gmail.com",
    val password: String = "daria123",
) : BaseViewState(screenState)