package com.spoonofcode.poa.feature.login.register

sealed interface RegisterViewAction {
    data class ChangeEmail(val email:String) : RegisterViewAction
    data class ChangeFirstName(val firstName: String) : RegisterViewAction
    data class ChangeLastName(val lastName: String) : RegisterViewAction
    data class ChangePassword(val password:String) : RegisterViewAction
    data object SignUp : RegisterViewAction
}