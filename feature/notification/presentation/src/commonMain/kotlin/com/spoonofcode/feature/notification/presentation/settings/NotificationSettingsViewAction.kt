package com.spoonofcode.feature.notification.presentation.settings

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationSettingsViewAction : BaseViewAction {
    data object InitView :
        com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction
    data object SaveChanges :
        com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction
    data class SwitchNotificationFromAllPartners(val enable: Boolean) :
        com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction

    data class SwitchNotificationFromMyPartners(val enable: Boolean) :
        com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction
}