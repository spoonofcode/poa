package com.spoonofcode.poa.feature.partner.panel

import com.spoonofcode.poa.core.ui.BaseViewAction

sealed interface PartnerPanelViewAction: BaseViewAction {
    data object NavigateToNotificationType : PartnerPanelViewAction
    data object NavigateToPartnerEdit : PartnerPanelViewAction
}