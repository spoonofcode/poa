package com.spoonofcode.feature.notification.presentation.type

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationTypeViewAction : BaseViewAction {
    data object SendNotificationToAllCustomers : NotificationTypeViewAction
    data object SendNotificationToSelectedPartnerCustomers : NotificationTypeViewAction
    data object SendNotificationToSelectedPartnerProductSeries : NotificationTypeViewAction
    data object SendNotificationToAllPartnerCustomers : NotificationTypeViewAction
}