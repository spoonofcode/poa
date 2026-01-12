package com.spoonofcode.poa.feature.profile.presentation.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.feature.profile.domain.usecase.GetProfileUseCase
import com.spoonofcode.poa.feature.profile.domain.usecase.UpdateProfileUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import kotlinx.coroutines.launch

internal class ProfileEditViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
) : BaseViewModel<ProfileEditViewState, ProfileEditViewAction>(ProfileEditViewState()) {

    override fun onAction(action: ProfileEditViewAction) {
        when (action) {
            is ProfileEditViewAction.ChangeCustomLink -> changeCustomLink(action.link)
            ProfileEditViewAction.InitView -> initView()
            ProfileEditViewAction.SaveChanges -> saveChanges()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getProfileUseCase()
                .onSuccess {
                    showContentView {
                        copy(
                            profile = it,
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun changeCustomLink(link: String) {
        val currentProfile = currentState().profile
        updateState { copy(profile = currentProfile?.copy(customLink = link)) }
    }

    private fun saveChanges() {
        showLoadingView()
        viewModelScope.launch {
            val profile = requireNotNull(currentState().profile)
            // TODO Fix this usecase
//                updateProfileUseCase(
//                    profileRequest = ProfileRequest(
//                        userId = profile.id,
//                        customLink = profile.customLink,
//                    )
//                )
            viewModelNavigator.pop()
        }
    }
}