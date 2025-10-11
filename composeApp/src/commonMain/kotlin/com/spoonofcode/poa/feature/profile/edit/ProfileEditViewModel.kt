package com.spoonofcode.poa.feature.profile.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.profile.GetProfileUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProfileEditViewModel(
    private val getProfileUseCase: GetProfileUseCase
) : BaseViewModel<ProfileEditViewState>(ProfileEditViewState()) {

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

    fun changeCustomLink(name: String) {
        val currentProfile = currentState().profile
        updateState { copy(profile = currentProfile?.copy(name = name)) }
    }

    fun saveChanges() {
        viewModelScope.launch {
            try {
                val profile = requireNotNull(currentState().profile)
//                editProfileUseCase(
//                    profileId = profile.id,
//                    productRequest = ProductRequest(
//                        name = product.name,
//                        description = product.description,
//                        imageLink = product.imageLink,
//                        videoLink = product.videoLink,
//                        collectionName = product.collectionName,
//                        websiteLink = product.websiteLink,
//                        customLink = product.customLink,
//                        seriesId = product.seriesId,
//                        tagId = product.tagId,
//                    )
//                )
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