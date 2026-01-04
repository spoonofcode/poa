package com.spoonofcode.poa.feature.notification.presentation.overview

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface NotificationOverviewViewAction: BaseViewAction {
    data object InitView :
        com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewAction
    data class SelectNotification(val notificationId: String) :
        com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewAction
}