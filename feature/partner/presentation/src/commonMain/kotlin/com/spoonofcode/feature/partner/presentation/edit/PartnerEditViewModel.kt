package com.spoonofcode.feature.partner.presentation.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerUseCase
import com.spoonofcode.feature.partner.domain.usecase.UpdatePartnerUseCase
import com.spoonofcode.feature.partner.presentation.PartnerEventBus
import kotlinx.coroutines.launch

internal class PartnerEditViewModel(
    private val getPartnerUseCase: GetPartnerUseCase,
    private val updatePartnerUseCase: UpdatePartnerUseCase,
    private val partnerEventBus: PartnerEventBus,
) : BaseViewModel<PartnerEditViewState, PartnerEditViewAction, Nothing>() {
    override val startAction = {
        initView()
    }

    override fun onAction(action: PartnerEditViewAction) {
        when (action) {
            is PartnerEditViewAction.ChangeDescription -> changeDescription(action.description)
            is PartnerEditViewAction.ChangeFacebookLink -> changeFacebookLink(action.link)
            is PartnerEditViewAction.ChangeInstagramLink -> changeInstagramLink(action.link)
            is PartnerEditViewAction.ChangeName -> changeName(action.name)
            is PartnerEditViewAction.ChangeWebsiteLink -> changeWebsiteLink(action.link)
            is PartnerEditViewAction.ChangeXLink -> changeXLink(action.link)
            is PartnerEditViewAction.ChangeYoutubeLink -> changeYoutubeLink(action.link)
            PartnerEditViewAction.SaveChanges -> saveChanges()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getPartnerUseCase()
                .onSuccess {
                    setContentState(
                        PartnerEditViewState(
                            partner = it
                        )
                    )
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun changeName(name: String) {
        updateContentState { copy(partner = getCurrentPartner().copy(name = name)) }
    }

    private fun changeDescription(description: String) {
        updateContentState { copy(partner = getCurrentPartner().copy(description = description)) }
    }

    private fun changeWebsiteLink(link: String) {
        updateContentState { copy(partner = getCurrentPartner().copy(websiteLink = link)) }
    }

    private fun changeInstagramLink(link: String) {
        updateContentState { copy(partner = getCurrentPartner().copy(instagramLink = link)) }
    }

    private fun changeFacebookLink(link: String) {
        updateContentState { copy(partner = getCurrentPartner().copy(facebookLink = link)) }
    }

    private fun changeYoutubeLink(link: String) {
        updateContentState { copy(partner = getCurrentPartner().copy(youtubeLink = link)) }
    }

    private fun changeXLink(link: String) {
        updateContentState { copy(partner = getCurrentPartner().copy(xLink = link)) }
    }

    private fun saveChanges() {
        updateContentState {
            copy(
                isSaveChangesButtonLoading = true
            )
        }
        viewModelScope.launch {
            updatePartnerUseCase(getCurrentPartner())
                .onSuccess {
                    partnerEventBus.emitPartnerUpdated()
                    viewModelNavigator.pop()
                }
                .onFailure {
                    updateContentState {
                        copy(
                            isSaveChangesButtonLoading = false
                        )
                    }
                    it.message?.let { message -> showErrorSnackbar(message) }
                }
        }
    }

    private fun getCurrentPartner(): Partner = requireNotNull(currentContentState().partner)
}