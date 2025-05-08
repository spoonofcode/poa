package com.spoonofcode.poa.feature.login.login

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class LoginViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val email: String = "bartosz.luczak@gmail.com",
    val password: String = "bartosz123",
) : BaseViewState()