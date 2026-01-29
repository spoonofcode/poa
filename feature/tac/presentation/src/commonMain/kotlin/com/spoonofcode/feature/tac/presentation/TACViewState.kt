package com.spoonofcode.feature.tac.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class TACViewState(
    val isPrivacyPolicyAccepted: Boolean = false,
    val isTermsAndConditionsAccepted: Boolean = false,
) : BaseViewState()