package com.spoonofcode.feature.tac.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import kotlinx.coroutines.launch

internal class TACViewModel() : BaseViewModel<TACViewState, TACViewAction>(
    initialViewState = TACViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: TACViewAction) {
        when (action) {
            TACViewAction.InitView -> initView()
            TACViewAction.SaveChanges -> saveChanges()
            is TACViewAction.ChangePrivacyPolicyAccepted -> {
                updateState { TACViewState(isPrivacyPolicyAccepted = action.accepted) }
            }
            is TACViewAction.ChangeTermsAndConditionsAccepted -> {
                updateState { TACViewState(isTermsAndConditionsAccepted = action.accepted) }
            }
        }
    }

    private fun initView() {
    }

    private fun saveChanges() {
        viewModelScope.launch {
            viewModelNavigator.pop()
        }
    }
}