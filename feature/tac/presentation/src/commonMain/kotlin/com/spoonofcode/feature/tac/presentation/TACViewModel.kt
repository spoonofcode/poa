package com.spoonofcode.feature.tac.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.tac.domain.usecase.GetTACSettingsUseCase
import com.spoonofcode.feature.tac.domain.usecase.UpdateTACSettingsUseCase
import kotlinx.coroutines.launch

internal class TACViewModel(
    private val getTACSettingsUseCase: GetTACSettingsUseCase,
    private val updateTACSettingsUseCase: UpdateTACSettingsUseCase,
) : BaseViewModel<TACViewState, TACViewAction, Nothing>() {
    override val startAction = {
        initView()
    }

    override fun onAction(action: TACViewAction) {
        when (action) {
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
                    setContentState(
                        TACViewState(
                            isPrivacyPolicyAccepted = tacSettings.isPrivacyPolicyAccepted,
                            isTermsAndConditionsAccepted = tacSettings.isTermsAndConditionsAccepted,
                            privacyPolicyLink = tacSettings.privacyPolicyLink,
                            marketingLink = tacSettings.marketingLink,
                        )
                    )
                    showContentView()
                }.onFailure {
                    it.message?.let { message -> showErrorSnackbar(message) }
                }
        }
    }

    private fun changeTermsAndConditions(accepted: Boolean) {
        updateContentState {
            copy(
                isTermsAndConditionsAccepted = accepted
            )
        }
    }

    private fun changePrivacyPolicy(accepted: Boolean) {
        updateContentState {
            copy(
                isPrivacyPolicyAccepted = accepted
            )
        }
    }

    private fun saveChanges() {
        updateContentState {
            copy(
                isSaveChangesButtonLoading = true
            )
        }
        viewModelScope.launch {
            updateTACSettingsUseCase(
                isPrivacyPolicyAccepted = currentContentState().isPrivacyPolicyAccepted,
                isTermsAndConditionsAccepted = currentContentState().isTermsAndConditionsAccepted,
            ).onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                updateContentState {
                    copy(
                        isSaveChangesButtonLoading = false
                    )
                }
                it.message?.let { message -> showErrorSnackbar(message) }
            }
        }
    }
}