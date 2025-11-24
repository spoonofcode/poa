package com.spoonofcode.poa.feature.notification.type

sealed interface NotificationTypeViewAction {
    data object NavigateToAll : NotificationTypeViewAction
    data object NavigateToPartnerCustomers : NotificationTypeViewAction
    data object NavigateToPartnerProductSeries : NotificationTypeViewAction
    data object NavigateToAllPartnerCustomers : NotificationTypeViewAction
}