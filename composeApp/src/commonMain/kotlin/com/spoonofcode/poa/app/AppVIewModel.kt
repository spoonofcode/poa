package com.spoonofcode.poa.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.data.network.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _state = MutableStateFlow<AppViewState>(AppViewState.Loading)
    val state: StateFlow<AppViewState> = _state.asStateFlow()

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            val isInitialized = sessionManager.isSessionInitialized()
            _state.value = if (isInitialized) {
                AppViewState.LoggedIn
            } else {
                AppViewState.LoggedOut
            }
        }
    }
}