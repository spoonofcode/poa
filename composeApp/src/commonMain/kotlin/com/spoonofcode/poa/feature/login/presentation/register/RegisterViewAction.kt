package com.spoonofcode.poa.feature.login.presentation.register

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface RegisterViewAction: BaseViewAction {
    data class ChangeEmail(val email:String) : RegisterViewAction
    data class ChangeFirstName(val firstName: String) : RegisterViewAction
    data class ChangeLastName(val lastName: String) : RegisterViewAction
    data class ChangePassword(val password:String) : RegisterViewAction
    data object SignUp : RegisterViewAction
}