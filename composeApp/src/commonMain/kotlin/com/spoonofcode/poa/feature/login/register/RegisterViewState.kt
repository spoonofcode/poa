package com.spoonofcode.poa.feature.login.register

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class RegisterViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
) : BaseViewState()