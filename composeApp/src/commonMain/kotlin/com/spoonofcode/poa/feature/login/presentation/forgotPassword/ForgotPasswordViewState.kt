package com.spoonofcode.poa.feature.login.presentation.forgotPassword

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState

@Immutable
internal data class ForgotPasswordViewState(
    val email: String = "",
): BaseViewState()