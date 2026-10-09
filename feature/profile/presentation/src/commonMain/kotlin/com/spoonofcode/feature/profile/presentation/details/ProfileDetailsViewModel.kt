package com.spoonofcode.feature.profile.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.appnavigation.FAQModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import com.spoonofcode.feature.profile.domain.usecase.GetProfileUseCase
import com.spoonofcode.feature.profile.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch

internal class ProfileDetailsViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel<ProfileDetailsViewState, ProfileDetailsViewAction, Nothing>() {
    override val startAction = {
        initView()
    }

    override fun onAction(action: ProfileDetailsViewAction) {
        when (action) {
            ProfileDetailsViewAction.Logout -> logout()
            ProfileDetailsViewAction.ConfirmLogout -> confirmLogout()
            ProfileDetailsViewAction.CancelLogout -> cancelLogout()
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
                    setContentState(
                        ProfileDetailsViewState(
                            profile = it,
                            partnerPanelVisible = it.roles.contains(PARTNER_ROLE)
                        )
                    )
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun navigateToEditProfile() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = ProfileModuleRoute.ProfileEdit)
        }
    }

    private fun navigateToPartnerPanel() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = PartnerModuleRoute.PartnerPanel)
        }
    }

    private fun navigateToNotificationSettings() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = NotificationModuleRoute.NotificationChannel)
        }
    }

    private fun navigateToTAC() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = TACModuleRoute.TAC())
        }
    }

    private fun navigateToFAQ() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = FAQModuleRoute.FAQ)
        }
    }

    private fun confirmLogout() {
        showLoadingView()
        viewModelScope.launch {
            logoutUseCase()
            viewModelNavigator.replaceAll(listOf(AuthModuleRoute.Login))
        }
    }

    private fun cancelLogout() {
        updateContentState { copy(isLogoutDialogVisible = false) }
    }

    private fun logout() {
        updateContentState { copy(isLogoutDialogVisible = true) }
    }

    companion object {
        const val PARTNER_ROLE = "partner"
    }
}