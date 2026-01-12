package com.spoonofcode.poa.feature.tac.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch

internal class TACViewModel() : BaseViewModel<TACViewState, TACViewAction>(
    initialViewState = TACViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: TACViewAction) {
        when (action) {
            TACViewAction.InitView -> initView()
            TACViewAction.SaveChanges -> saveChanges()
        }
    }

    private fun initView() {
        showLoadingView()
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

    private fun saveChanges() {
        viewModelScope.launch {
            viewModelNavigator.pop()
        }
    }
}