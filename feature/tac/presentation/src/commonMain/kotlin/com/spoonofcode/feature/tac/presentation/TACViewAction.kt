package com.spoonofcode.feature.tac.presentation

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface TACViewAction : BaseViewAction {
    data object SaveChanges : TACViewAction

    data class ChangePrivacyPolicy(
        val accepted: Boolean,
    ) : TACViewAction

    data class ChangeTermsAndConditions(
        val accepted: Boolean,
    ) : TACViewAction
}
