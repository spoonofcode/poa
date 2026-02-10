package com.spoonofcode.feature.notification.presentation.overview

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationOverviewViewAction: BaseViewAction {
    data object InitView :
        com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewAction
    data class SelectNotification(val notificationId: String) :
        com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewAction
}