package com.spoonofcode.poa.feature.faq

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.faq.GetFAQUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
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
                            screenState = ScreenState.CONTENT,
                            faqs = it,
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