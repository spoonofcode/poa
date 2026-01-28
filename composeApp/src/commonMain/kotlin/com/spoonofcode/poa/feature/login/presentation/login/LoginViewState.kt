package com.spoonofcode.poa.feature.login.presentation.login

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class LoginViewState(
//    val email: String = "daria.waszkiewicz@gmail.com",
    val email: String = "luczak.bartosz5@gmail.com",
    val password: String = "daria123",
    val isSignInButtonEnable: Boolean = false,
) : BaseViewState()