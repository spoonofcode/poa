package com.spoonofcode.feature.partner.presentation.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerUseCase
import com.spoonofcode.feature.partner.domain.usecase.UpdatePartnerUseCase
import kotlinx.coroutines.launch

internal class PartnerEditViewModel(
    private val getPartnerUseCase: GetPartnerUseCase,
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
            is PartnerEditViewAction.InitView -> initView()
            PartnerEditViewAction.SaveChanges -> saveChanges()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getPartnerUseCase()
                .onSuccess {
                    showContentView {
                        copy(
                            partner = it
                        )
                    }
                }
                .onFailure {
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
            updatePartnerUseCase(getCurrentPartner())
                .onSuccess {
                    viewModelNavigator.pop()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun getCurrentPartner(): Partner = requireNotNull(currentState().partner)
}