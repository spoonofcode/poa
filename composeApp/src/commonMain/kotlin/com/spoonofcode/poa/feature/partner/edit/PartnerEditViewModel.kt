package com.spoonofcode.poa.feature.partner.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetPartnerByIdUseCase
import com.spoonofcode.poa.core.domain.partner.UpdatePartnerUseCase
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class PartnerEditViewModel(
    private val getPartnerByIdUseCase: GetPartnerByIdUseCase,
    private val updatePartnerUseCase: UpdatePartnerUseCase
) : BaseViewModel<PartnerEditViewState>(PartnerEditViewState()) {

    fun initView(partnerId: Int) {
        viewModelScope.launch {
            showLoadingView()
            try {
                val partner = getPartnerByIdUseCase(partnerId = partnerId)
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        partner = partner
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun changeName(name: String) {
        updateState { copy(partner = getCurrentPartner().copy(name = name)) }
    }

    fun changeDescription(description: String) {
        updateState { copy(partner = getCurrentPartner().copy(description = description)) }
    }

    fun changeWebsiteLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(websiteLink = link)) }
    }

    fun changeInstagramLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(instagramLink = link)) }
    }

    fun changeFacebookLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(facebookLink = link)) }
    }

    fun changeYoutubeLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(youtubeLink = link)) }
    }

    fun changeXLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(xLink = link)) }
    }

    fun saveChanges() {
        viewModelScope.launch {
            showLoadingView()
            try {
                updatePartnerUseCase(getCurrentPartner())
                viewModelNavigator.pop()
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorView()
            }
        }
    }

    private fun getCurrentPartner(): Partner = requireNotNull(currentState().partner)

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