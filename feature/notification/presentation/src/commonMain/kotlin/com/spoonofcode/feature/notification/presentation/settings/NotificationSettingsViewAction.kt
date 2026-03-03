package com.spoonofcode.feature.notification.presentation.settings

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationSettingsViewAction : BaseViewAction {
    data object InitView :
        NotificationSettingsViewAction
    data object SaveChanges :
        NotificationSettingsViewAction
    data class SwitchNotificationFromAllPartners(val enable: Boolean) :
        NotificationSettingsViewAction

    data class SwitchNotificationFromMyPartners(val enable: Boolean) :
        NotificationSettingsViewAction
}