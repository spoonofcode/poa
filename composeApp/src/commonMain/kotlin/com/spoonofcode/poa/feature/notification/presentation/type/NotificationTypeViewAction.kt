package com.spoonofcode.poa.feature.notification.presentation.type

import com.spoonofcode.poa.core.ui.base.BaseViewAction

internal sealed interface NotificationTypeViewAction: BaseViewAction {
    data object NavigateToAllCustomers :
        com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction
    data object NavigateToPartnerCustomers :
        com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction
    data object NavigateToPartnerProductSeries :
        com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction
    data object NavigateToAllPartnerCustomers :
        com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction
}