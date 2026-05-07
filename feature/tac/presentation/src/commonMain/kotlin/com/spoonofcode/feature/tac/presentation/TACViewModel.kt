package com.spoonofcode.feature.tac.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.tac.domain.model.TACSettings
import com.spoonofcode.feature.tac.domain.usecase.GetTACSettingsUseCase
import com.spoonofcode.feature.tac.domain.usecase.UpdateTACSettingsUseCase
import kotlinx.coroutines.launch

internal class TACViewModel(
    private val getTACSettingsUseCase: GetTACSettingsUseCase,
    private val updateTACSettingsUseCase: UpdateTACSettingsUseCase,
) : BaseViewModel<TACViewState, TACViewAction>(
    initialViewState = TACViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: TACViewAction) {
        when (action) {
            TACViewAction.InitView -> initView()
            TACViewAction.SaveChanges -> saveChanges()
            is TACViewAction.ChangePrivacyPolicy -> changePrivacyPolicy(action.accepted)
            is TACViewAction.ChangeTermsAndConditions -> changeTermsAndConditions(action.accepted)
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getTACSettingsUseCase()
                .onSuccess { tacSettings ->
                    showContentView {
                        copy(
                            isPrivacyPolicyAccepted = tacSettings.isPrivacyPolicyAccepted,
                            isTermsAndConditionsAccepted = tacSettings.isTermsAndConditionsAccepted,
                        )
                    }
                }.onFailure {
                    showErrorSnackbar(it)
                }
        }
    }

    private fun changeTermsAndConditions(accepted: Boolean) {
        updateState {
            copy(
                isTermsAndConditionsAccepted = accepted
            )
        }
    }

    private fun changePrivacyPolicy(accepted: Boolean) {
        updateState {
            copy(
                isPrivacyPolicyAccepted = accepted
            )
        }
    }

    private fun saveChanges() {
        viewModelScope.launch {
            showLoadingView()
            updateTACSettingsUseCase(
                tacSettings = TACSettings(
                    isPrivacyPolicyAccepted = currentContentViewState().isPrivacyPolicyAccepted,
                    isTermsAndConditionsAccepted = currentContentViewState().isTermsAndConditionsAccepted,
                )
            ).onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }
}