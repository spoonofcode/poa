package com.spoonofcode.feature.partner.presentation.panel

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerPanelViewAction : BaseViewAction {
    data object NavigateToNotificationType : PartnerPanelViewAction
    data object NavigateToPartnerEdit : PartnerPanelViewAction
    data object NavigateToReports : PartnerPanelViewAction
}