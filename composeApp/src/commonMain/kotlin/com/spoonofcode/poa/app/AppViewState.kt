package com.spoonofcode.poa.app

sealed class AppViewState {
    data object Loading : AppViewState()
    data object LoggedIn : AppViewState()
    data object LoggedOut : AppViewState()
}