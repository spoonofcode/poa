package com.spoonofcode.poa.feature.profile

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.profile.GetProfileUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.faq.FAQScreen
import com.spoonofcode.poa.feature.partner.panel.PartnerPanelScreen
import com.spoonofcode.poa.feature.profile.edit.ProfileEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase
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

    fun navigateToFAQ() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = FAQScreen())
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