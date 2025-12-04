package com.spoonofcode.poa.feature.partner.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetPartnerByIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch

internal class PartnerDetailsViewModel(
    private val getPartnerByIdUseCase: GetPartnerByIdUseCase,
) : BaseViewModel<PartnerDetailsViewState, PartnerDetailsViewAction>(PartnerDetailsViewState()) {

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
                    updateState {
                        copy(
                            screenState = ScreenState.CONTENT,
                            partner = it
                        )
                    }
                }
                .onFailure {
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