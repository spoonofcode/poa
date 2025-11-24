package com.spoonofcode.poa.feature.notification.settings

sealed interface NotificationSettingsViewAction {
    data object InitView : NotificationSettingsViewAction
    data object SaveChanges : NotificationSettingsViewAction
    data class SwitchNotificationFromAllPartners(val enable: Boolean) : NotificationSettingsViewAction
    data class SwitchNotificationFromMyPartners(val enable: Boolean) : NotificationSettingsViewAction
}