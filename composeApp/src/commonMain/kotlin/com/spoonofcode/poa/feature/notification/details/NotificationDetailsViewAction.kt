package com.spoonofcode.poa.feature.notification.details

import com.spoonofcode.poa.core.ui.BaseViewAction

sealed interface NotificationDetailsViewAction: BaseViewAction {
    data class InitView(val notificationId: String) : NotificationDetailsViewAction
}