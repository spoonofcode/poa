package com.spoonofcode.feature.profile.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.poa.feature.faq.presentation.FAQScreen
import com.spoonofcode.poa.feature.login.presentation.login.LoginScreen
import com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsScreen
import com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelScreen
import com.spoonofcode.poa.feature.profile.domain.usecase.GetProfileUseCase
import com.spoonofcode.poa.feature.profile.domain.usecase.LogoutUseCase
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditScreen
import com.spoonofcode.poa.feature.tac.presentation.TACScreen
import kotlinx.coroutines.launch

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
            getProfileUseCase()
                .onSuccess {
                    showContentView {
                        ProfileDetailsViewState(
                            profile = it,
                        )
                    }
                }
                .onFailure {
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
            logoutUseCase()
            viewModelNavigator.replaceAll(listOf(LoginScreen()))
        }
    }
}