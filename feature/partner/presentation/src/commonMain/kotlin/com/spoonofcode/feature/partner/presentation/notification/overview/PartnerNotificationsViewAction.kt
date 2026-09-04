package com.spoonofcode.feature.partner.presentation.notification.overview

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerNotificationsViewAction : BaseViewAction {
    data class SelectNotification(val notificationId: String) : PartnerNotificationsViewAction
}