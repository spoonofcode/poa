package com.spoonofcode.poa.feature.notification.presentation.settings

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface NotificationSettingsViewAction: BaseViewAction {
    data object InitView :
        com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction
    data object SaveChanges :
        com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction
    data class SwitchNotificationFromAllPartners(val enable: Boolean) :
        com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction
    data class SwitchNotificationFromMyPartners(val enable: Boolean) :
        com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction
}