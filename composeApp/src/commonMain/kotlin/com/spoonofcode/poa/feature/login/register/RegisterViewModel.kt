package com.spoonofcode.poa.feature.login.register

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.domain.RegisterUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel<RegisterViewState>(RegisterViewState()) {

    fun changeEmail(email: String) {
        viewModelScope.launch {
            updateState {
                copy(email = email)
            }
        }
    }

    fun changePassword(password: String) {
        viewModelScope.launch {
            updateState {
                copy(password = password)
            }
        }
    }

    fun changeFirstName(firstName: String) {
        viewModelScope.launch {
            updateState {
                copy(firstName = firstName)
            }
        }
    }

    fun changeLastName(lastName: String) {
        viewModelScope.launch {
            updateState {
                copy(lastName = lastName)
            }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            showLoadingView()
            try {
                registerUseCase(
                    email = viewState.value.email,
                    password = viewState.value.password,
                    firstName = viewState.value.firstName,
                    lastName = viewState.value.lastName,
                )
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                isLoadingView = false
            )
        }
    }
}