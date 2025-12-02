package com.spoonofcode.poa.feature.login.register

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.domain.login.RegisterUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel<RegisterViewState, RegisterViewAction>(RegisterViewState()) {

    override fun onAction(action: RegisterViewAction) {
        when (action) {
            is RegisterViewAction.ChangeEmail -> changeEmail(action.email)
            is RegisterViewAction.ChangeFirstName -> changeFirstName(action.firstName)
            is RegisterViewAction.ChangeLastName -> changeLastName(action.lastName)
            is RegisterViewAction.ChangePassword -> changePassword(action.password)
            RegisterViewAction.SignUp -> signUp()
        }
    }

    private fun changeEmail(email: String) {
        updateState {
            copy(email = email)
        }
    }

    private fun changePassword(password: String) {
        updateState {
            copy(password = password)
        }
    }

    private fun changeFirstName(firstName: String) {
        updateState {
            copy(firstName = firstName)
        }
    }

    private fun changeLastName(lastName: String) {
        updateState {
            copy(lastName = lastName)
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val currentState = currentState()
                registerUseCase(
                    email = currentState.email,
                    password = currentState.password,
                    firstName = currentState.firstName,
                    lastName = currentState.lastName,
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
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                screenState = ScreenState.CONTENT,
            )
        }
    }
}