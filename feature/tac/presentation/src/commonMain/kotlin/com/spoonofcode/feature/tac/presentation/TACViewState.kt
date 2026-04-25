package com.spoonofcode.feature.tac.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class TACViewState(
    val isPrivacyPolicyAccepted: Boolean = false,
    val isTermsAndConditionsAccepted: Boolean = false,
    val privacyPolicyLink: String = "https://proof-of-wear.com/privacy-mobile.html",
    val marketingLink: String = "https://proof-of-wear.com/marketing-mobile.html",
) : BaseViewState() {
    val isSaveChangesButtonEnable: Boolean
        get() = isPrivacyPolicyAccepted && isTermsAndConditionsAccepted
}