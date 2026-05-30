package com.spoonofcode.feature.notification.presentation.partner

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerNotificationsViewAction : BaseViewAction {
    data object InitView : PartnerNotificationsViewAction
    data class SelectNotification(val notificationId: String) : PartnerNotificationsViewAction
}