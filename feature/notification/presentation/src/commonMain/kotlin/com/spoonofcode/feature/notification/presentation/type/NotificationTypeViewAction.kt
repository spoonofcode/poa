package com.spoonofcode.feature.notification.presentation.type

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationTypeViewAction : BaseViewAction {
    data object NavigateToAllCustomers : NotificationTypeViewAction
    data object NavigateToPartnerCustomers : NotificationTypeViewAction
    data object NavigateToPartnerProductSeries : NotificationTypeViewAction
    data object NavigateToAllPartnerCustomers : NotificationTypeViewAction
}