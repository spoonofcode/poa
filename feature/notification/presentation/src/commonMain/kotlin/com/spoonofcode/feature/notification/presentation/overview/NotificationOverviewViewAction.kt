package com.spoonofcode.feature.notification.presentation.overview

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationOverviewViewAction: BaseViewAction {
    data object InitView : NotificationOverviewViewAction
    data class SelectNotification(val notificationId: String) : NotificationOverviewViewAction
}