package com.spoonofcode.feature.partner.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerByIdUseCase
import kotlinx.coroutines.launch

internal class PartnerDetailsViewModel(
    private val getPartnerByIdUseCase: GetPartnerByIdUseCase,
) : BaseViewModel<PartnerDetailsViewState, PartnerDetailsViewAction, Nothing>(
    initialViewState = ViewState.Content(PartnerDetailsViewState()),
) {

    override fun onAction(action: PartnerDetailsViewAction) {
        when (action) {
            is PartnerDetailsViewAction.InitView -> initView(action.partnerId)
        }
    }

    private fun initView(partnerId: String) {
        showLoadingView()
        viewModelScope.launch {
            getPartnerByIdUseCase(partnerId = partnerId)
                .onSuccess {
                    updateContentState {
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
}