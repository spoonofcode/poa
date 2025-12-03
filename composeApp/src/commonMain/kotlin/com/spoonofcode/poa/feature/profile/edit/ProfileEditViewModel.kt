package com.spoonofcode.poa.feature.profile.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.profile.GetProfileUseCase
import com.spoonofcode.poa.core.domain.profile.UpdateProfileUseCase
import com.spoonofcode.poa.core.model.ProfileRequest
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

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

    private fun changeCustomLink(link: String) {
        val currentProfile = currentState().profile
        updateState { copy(profile = currentProfile?.copy(customLink = link)) }
    }

    private fun saveChanges() {
        viewModelScope.launch {
            try {
                val profile = requireNotNull(currentState().profile)
                // TODO Fix this usecase
//                updateProfileUseCase(
//                    userId = profile.id,
//                    profileRequest = ProfileRequest(
//                        userId = profile.id,
//                        customLink = profile.customLink,
//                    )
//                )
                viewModelNavigator.pop()
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

    private fun showErrorView() {
        updateState {
            copy(
                screenState = ScreenState.ERROR,
            )
        }
    }

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                screenState = ScreenState.LOADING
            )
        }
    }
}