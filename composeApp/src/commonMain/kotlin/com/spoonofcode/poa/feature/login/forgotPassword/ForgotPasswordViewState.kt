package com.spoonofcode.poa.feature.login.forgotPassword

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class ForgotPasswordViewState(
    override val isLoadingView: Boolean = false,
    val email: String = "",
): BaseViewState()