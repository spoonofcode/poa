package com.spoonofcode.poa.feature.notification.overview

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface NotificationOverviewViewAction: BaseViewAction {
    data object InitView : NotificationOverviewViewAction
    data class SelectNotification(val notificationId: String) : NotificationOverviewViewAction
}