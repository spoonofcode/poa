package com.spoonofcode.feature.notification.presentation.details

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationDetailsViewAction : BaseViewAction {
    data class InitView(val notificationId: String) : NotificationDetailsViewAction
}