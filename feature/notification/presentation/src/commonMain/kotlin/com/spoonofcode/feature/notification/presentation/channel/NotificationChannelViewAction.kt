package com.spoonofcode.feature.notification.presentation.channel

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationChannelViewAction : BaseViewAction {
    data object SelectNotificationPush : NotificationChannelViewAction
}