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
) : BaseViewModel<PartnerEditViewState, PartnerEditViewAction>(PartnerEditViewState()) {

    override fun onAction(action: PartnerEditViewAction) {
        when (action) {
            is PartnerEditViewAction.ChangeDescription -> changeDescription(action.description)
            is PartnerEditViewAction.ChangeFacebookLink -> changeFacebookLink(action.link)
            is PartnerEditViewAction.ChangeInstagramLink -> changeInstagramLink(action.link)
            is PartnerEditViewAction.ChangeName -> changeName(action.name)
            is PartnerEditViewAction.ChangeWebsiteLink -> changeWebsiteLink(action.link)
            is PartnerEditViewAction.ChangeXLink -> changeXLink(action.link)
            is PartnerEditViewAction.ChangeYoutubeLink -> changeYoutubeLink(action.link)
            is PartnerEditViewAction.InitView -> initView(action.partnerId)
            PartnerEditViewAction.SaveChanges -> saveChanges()
        }
    }

    private fun initView(partnerId: String) {
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

    private fun changeName(name: String) {
        updateState { copy(partner = getCurrentPartner().copy(name = name)) }
    }

    private fun changeDescription(description: String) {
        updateState { copy(partner = getCurrentPartner().copy(description = description)) }
    }

    private fun changeWebsiteLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(websiteLink = link)) }
    }

    private fun changeInstagramLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(instagramLink = link)) }
    }

    private fun changeFacebookLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(facebookLink = link)) }
    }

    private fun changeYoutubeLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(youtubeLink = link)) }
    }

    private fun changeXLink(link: String) {
        updateState { copy(partner = getCurrentPartner().copy(xLink = link)) }
    }

    private fun saveChanges() {
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