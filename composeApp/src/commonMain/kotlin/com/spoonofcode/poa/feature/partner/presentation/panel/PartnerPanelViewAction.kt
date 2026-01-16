package com.spoonofcode.poa.feature.partner.presentation.panel

import com.spoonofcode.poa.core.ui.base.BaseViewAction

internal sealed interface PartnerPanelViewAction : BaseViewAction {
    data object NavigateToNotificationType : PartnerPanelViewAction
    data object NavigateToPartnerEdit : PartnerPanelViewAction
    data object NavigateToReports : PartnerPanelViewAction
}