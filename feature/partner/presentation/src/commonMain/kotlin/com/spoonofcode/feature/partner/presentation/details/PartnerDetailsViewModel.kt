package com.spoonofcode.feature.partner.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerByIdUseCase
import kotlinx.coroutines.launch

internal class PartnerDetailsViewModel(
    private val partnerId: String,
    private val getPartnerByIdUseCase: GetPartnerByIdUseCase,
) : BaseViewModel<PartnerDetailsViewState, PartnerDetailsViewAction, Nothing>() {
    override val startAction = {
        initView(partnerId)
    }

    private fun initView(partnerId: String) {
        showLoadingView()
        viewModelScope.launch {
            getPartnerByIdUseCase(partnerId = partnerId)
                .onSuccess {
                    setContentState(
                        PartnerDetailsViewState(
                            partner = it,
                        ),
                    )
                    showContentView()
                }.onFailure {
                    showErrorView()
                }
        }
    }
}
