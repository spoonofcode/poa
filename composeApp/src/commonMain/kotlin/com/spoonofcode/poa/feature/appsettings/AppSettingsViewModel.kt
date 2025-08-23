package com.spoonofcode.poa.feature.appsettings

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.DeleteAccountUseCase
import com.spoonofcode.poa.core.domain.login.LogoutUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.login.login.LoginScreen
import com.spoonofcode.poa.feature.messagefcm.MessageFCMScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class AppSettingsViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
) : BaseViewModel<AppSettingsViewState>(AppSettingsViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun navigateToMessageFCM() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = MessageFCMScreen())
        }
    }

    fun signOut() {
        viewModelScope.launch {
            logoutUseCase()
            viewModelNavigator.replaceAll(listOf(LoginScreen()))
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            deleteAccountUseCase()
            viewModelNavigator.replaceAll(listOf(LoginScreen()))
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                screenState = ScreenState.ERROR,
            )
        }
    }
}