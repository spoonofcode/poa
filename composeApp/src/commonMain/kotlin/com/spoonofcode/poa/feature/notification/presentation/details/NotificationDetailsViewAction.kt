package com.spoonofcode.poa.feature.notification.presentation.details

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface NotificationDetailsViewAction: BaseViewAction {
    data class InitView(val notificationId: String) :
        com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewAction
}