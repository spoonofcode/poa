package com.spoonofcode.feature.partner.presentation.notification.type

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerNotificationTypeViewAction : BaseViewAction {
    data object SendNotificationToAllCustomers : PartnerNotificationTypeViewAction

    data object SendNotificationToSelectedPartnerCustomers : PartnerNotificationTypeViewAction

    data object SendNotificationToSelectedPartnerProductSeries : PartnerNotificationTypeViewAction

    data object SendNotificationToAllPartnerCustomers : PartnerNotificationTypeViewAction
}
