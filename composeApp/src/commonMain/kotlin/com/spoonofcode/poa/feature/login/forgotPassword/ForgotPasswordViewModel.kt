package com.spoonofcode.poa.feature.login.forgotPassword

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.core.ui.BaseViewModel
import kotlinx.coroutines.launch

internal class ForgotPasswordViewModel(
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionManager: SessionManager,
) : BaseViewModel<ForgotPasswordViewState>(ForgotPasswordViewState()) {

    fun changeEmail(email: String) {
        viewModelScope.launch {
            updateState {
                copy(
                    email = email,
                    isLoadingView = false,
                )
            }
        }
    }

    fun resetPassword() {
        viewModelScope.launch {

        }
    }
}