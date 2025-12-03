package com.spoonofcode.poa.feature.notification.type

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface NotificationTypeViewAction: BaseViewAction {
    data object NavigateToAllCustomers : NotificationTypeViewAction
    data object NavigateToPartnerCustomers : NotificationTypeViewAction
    data object NavigateToPartnerProductSeries : NotificationTypeViewAction
    data object NavigateToAllPartnerCustomers : NotificationTypeViewAction
}