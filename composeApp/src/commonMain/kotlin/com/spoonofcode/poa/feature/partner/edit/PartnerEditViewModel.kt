package com.spoonofcode.poa.feature.partner.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetPartnerByIdUseCase
import com.spoonofcode.poa.core.domain.partner.UpdatePartnerUseCase
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import com.spoonofcode.poa.core.ui.ScreenState

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
                        isLoadingView = false,
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
        val currentPartner = currentState().partner
        updateState { copy(partner = currentPartner.copy(name = name)) }
    }

    fun changeDescription(description: String) {
        val currentPartner = currentState().partner
        updateState { copy(partner = currentPartner.copy(description = description)) }
    }

    fun changeWebsiteLink(link: String) {
        val currentPartner = currentState().partner
        updateState { copy(partner = currentPartner.copy(websiteLink = link)) }
    }

    fun changeInstagramLink(link: String) {
        val currentPartner = currentState().partner
        updateState { copy(partner = currentPartner.copy(instagramLink = link)) }
    }

    fun changeFacebookLink(link: String) {
        val currentPartner = currentState().partner
        updateState { copy(partner = currentPartner.copy(facebookLink = link)) }
    }

    fun changeYoutubeLink(link: String) {
        val currentPartner = currentState().partner
        updateState { copy(partner = currentPartner.copy(youtubeLink = link)) }
    }

    fun changeXLink(link: String) {
        val currentPartner = currentState().partner
        updateState { copy(partner = currentPartner.copy(xLink = link)) }
    }

    fun saveChanges() {
        viewModelScope.launch {
            showLoadingView()
            try {
                updatePartnerUseCase(viewState.value.partner)
                viewModelNavigator.pop()
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorView()
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
}