package com.spoonofcode.poa.feature.profile

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.login.LogoutUseCase
import com.spoonofcode.poa.core.domain.profile.GetProfileUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.faq.FAQScreen
import com.spoonofcode.poa.feature.login.login.LoginScreen
import com.spoonofcode.poa.feature.notification.settings.NotificationSettingsScreen
import com.spoonofcode.poa.feature.partner.panel.PartnerPanelScreen
import com.spoonofcode.poa.feature.profile.edit.ProfileEditScreen
import com.spoonofcode.poa.feature.tac.TACScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel<ProfileViewState>(ProfileViewState()) {

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val profile = getProfileUseCase()
                updateState {
                    copy(
                        profile = profile,
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

    fun navigateToEditProfile() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = ProfileEditScreen())
        }
    }

    fun navigateToPartnerPanel() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = PartnerPanelScreen())
        }
    }

    fun navigateToNotificationSettings() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = NotificationSettingsScreen())
        }
    }

    fun navigateToTAC() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = TACScreen())
        }
    }

    fun navigateToFAQ() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = FAQScreen())
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke()
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