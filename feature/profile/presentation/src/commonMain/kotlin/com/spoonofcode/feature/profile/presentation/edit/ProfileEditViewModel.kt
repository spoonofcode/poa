package com.spoonofcode.feature.profile.presentation.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.feature.profile.domain.usecase.GetProfileUseCase
import com.spoonofcode.feature.profile.domain.usecase.UpdateProfileUseCase
import kotlinx.coroutines.launch
import com.spoonofcode.core.presentation.Res as CoreRes

internal class ProfileEditViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
) : BaseViewModel<ProfileEditViewState, ProfileEditViewAction, Nothing>() {
    override val startAction = {
        initView()
    }

    override fun onAction(action: ProfileEditViewAction) {
        when (action) {
            is ProfileEditViewAction.ChangeCustomLink -> changeCustomLink(action.link)
            ProfileEditViewAction.SaveChanges -> saveChanges()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getProfileUseCase()
                .onSuccess {
                    setContentState(
                        ProfileEditViewState(
                            profile = it,
                        ),
                    )
                    showContentView()
                }.onFailure {
                    showErrorView()
                }
        }
    }

    private fun changeCustomLink(link: String) {
        val currentProfile = currentContentState().profile
        updateContentState { ProfileEditViewState(profile = currentProfile.copy(email = link)) }
    }

    private fun saveChanges() {
        updateContentState { copy(isSaveChangesButtonLoading = true) }
        viewModelScope.launch {
            updateProfileUseCase()
                .onSuccess {
                    viewModelNavigator.pop()
                }.onFailure {
                    updateContentState { copy(isSaveChangesButtonLoading = false) }
                    showErrorSnackbar(UiText.Resource(CoreRes.string.default_error))
                }
        }
    }
}
