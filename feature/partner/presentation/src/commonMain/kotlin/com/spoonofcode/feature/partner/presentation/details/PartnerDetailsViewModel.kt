package com.spoonofcode.feature.partner.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
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
}