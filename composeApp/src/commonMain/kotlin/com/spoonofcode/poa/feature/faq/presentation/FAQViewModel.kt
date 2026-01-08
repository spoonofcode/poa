package com.spoonofcode.poa.feature.faq.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.feature.faq.domain.usecase.GetFAQUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import kotlinx.coroutines.launch

internal class FAQViewModel(
    private val getFAQUseCase: GetFAQUseCase,
) : BaseViewModel<FAQViewState, FAQViewAction>(FAQViewState()) {

    override fun onAction(action: FAQViewAction) {
        when (action) {
            FAQViewAction.InitView -> initView()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getFAQUseCase()
                .onSuccess {
                    updateState {
                        copy(
                            faqs = it,
                        )
                    }
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }
}