package com.spoonofcode.feature.notification.presentation.type

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationTypeViewAction: BaseViewAction {
    data object NavigateToAllCustomers :
        com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction
    data object NavigateToPartnerCustomers :
        com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction
    data object NavigateToPartnerProductSeries :
        com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction
    data object NavigateToAllPartnerCustomers :
        com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction
}