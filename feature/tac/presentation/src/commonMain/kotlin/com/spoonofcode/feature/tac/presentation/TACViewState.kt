package com.spoonofcode.feature.tac.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class TACViewState(
    val isPrivacyPolicyAccepted: Boolean,
    val isTermsAndConditionsAccepted: Boolean,
    val privacyPolicyLink: String,
    val marketingLink: String,
    val isSaveChangesButtonLoading: Boolean = false,
) : BaseViewState() {
    val isViewEnable: Boolean
        get() = !isSaveChangesButtonLoading

    val isSaveChangesButtonEnable: Boolean
        get() = isPrivacyPolicyAccepted && isTermsAndConditionsAccepted
}