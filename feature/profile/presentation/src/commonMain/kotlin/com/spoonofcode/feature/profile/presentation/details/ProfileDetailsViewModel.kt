package com.spoonofcode.feature.profile.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.profile.domain.usecase.GetProfileUseCase
import com.spoonofcode.feature.profile.domain.usecase.LogoutUseCase
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditScreen
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
        // TODO #109 Fix after migration to feature modules
//        viewModelScope.launch {
//            viewModelNavigator.push(screen = PartnerPanelScreen())
//        }
    }

    private fun navigateToNotificationSettings() {
        // TODO #109 Fix after migration to feature modules
//        viewModelScope.launch {
//            viewModelNavigator.push(screen = NotificationSettingsScreen())
//        }
    }

    private fun navigateToTAC() {
        // TODO #109 Fix after migration to feature modules
//        viewModelScope.launch {
//            viewModelNavigator.push(screen = TACScreen())
//        }
    }

    private fun navigateToFAQ() {
        // TODO #109 Fix after migration to feature modules
//        viewModelScope.launch {
//            viewModelNavigator.push(screen = FAQScreen())
//        }
    }

    private fun logout() {
        // TODO #109 Fix after migration to feature modules
//        viewModelScope.launch {
//            logoutUseCase()
//            viewModelNavigator.replaceAll(listOf(LoginScreen()))
//        }
    }
}