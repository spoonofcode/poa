package com.spoonofcode.poa.feature.profile.details

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

internal class ProfileDetailsViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel<ProfileDetailsViewState, ProfileDetailsViewAction>(ProfileDetailsViewState()) {

    override fun onAction(action: ProfileDetailsViewAction) {
        when (action) {
            ProfileDetailsViewAction.InitView -> initView()
            ProfileDetailsViewAction.Logout -> logout()
            ProfileDetailsViewAction.NavigateToEditProfile -> navigateToEditProfile()
            ProfileDetailsViewAction.NavigateToFAQ -> navigateToFAQ()
            ProfileDetailsViewAction.NavigateToNotificationSettings -> navigateToNotificationSettings()
            ProfileDetailsViewAction.NavigateToPartnerPanel -> navigateToPartnerPanel()
            ProfileDetailsViewAction.NavigateToTAC -> navigateToTAC()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
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

    private fun navigateToEditProfile() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = ProfileEditScreen())
        }
    }

    private fun navigateToPartnerPanel() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = PartnerPanelScreen())
        }
    }

    private fun navigateToNotificationSettings() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = NotificationSettingsScreen())
        }
    }

    private fun navigateToTAC() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = TACScreen())
        }
    }

    private fun navigateToFAQ() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = FAQScreen())
        }
    }

    private fun logout() {
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