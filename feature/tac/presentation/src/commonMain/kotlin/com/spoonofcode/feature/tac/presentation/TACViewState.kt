package com.spoonofcode.feature.tac.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class TACViewState(
    val isPrivacyPolicyAccepted: Boolean,
    val isTermsAndConditionsAccepted: Boolean,
    val privacyPolicyLink: String,
    val marketingLink: String,
) : BaseViewState() {
    val isSaveChangesButtonEnable: Boolean
        get() = isPrivacyPolicyAccepted && isTermsAndConditionsAccepted
}