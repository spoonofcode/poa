package com.spoonofcode.poa.feature.tac

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.faq.FAQViewState
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class TACViewModel() : BaseViewModel<TACViewState>(TACViewState()) {
    fun initView() {
        viewModelScope.launch {
//            showLoadingView()
//            try {
//                updateState {
//                    copy(
//                        screenState = ScreenState.CONTENT,
//                        faqItems = faqItems,
//                    )
//                }
//            } catch (ce: CancellationException) {
//                throw ce
//            } catch (e: Exception) {
//                showErrorView()
//            }
        }
    }

    fun saveChanges() {
        viewModelScope.launch {
            viewModelNavigator.pop()
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