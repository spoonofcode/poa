package com.spoonofcode.poa.feature.partner.panel

sealed interface PartnerPanelViewAction {
    data object NavigateToNotificationType : PartnerPanelViewAction
    data object NavigateToPartnerEdit : PartnerPanelViewAction
}