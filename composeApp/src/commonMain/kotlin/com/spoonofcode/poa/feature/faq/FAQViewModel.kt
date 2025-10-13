package com.spoonofcode.poa.feature.faq

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class FAQViewModel() : BaseViewModel<FAQViewState>(FAQViewState()) {
    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        faqItems = faqItems,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
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