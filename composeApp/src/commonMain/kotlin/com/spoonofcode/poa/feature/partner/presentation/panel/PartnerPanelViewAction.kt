package com.spoonofcode.poa.feature.partner.presentation.panel

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface PartnerPanelViewAction: BaseViewAction {
    data object NavigateToNotificationType :
        com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction
    data object NavigateToPartnerEdit :
        com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction
    data object NavigateToReports :
        com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction
}