package com.spoonofcode.poa.feature.notification.settings

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface NotificationSettingsViewAction: BaseViewAction {
    data object InitView : NotificationSettingsViewAction
    data object SaveChanges : NotificationSettingsViewAction
    data class SwitchNotificationFromAllPartners(val enable: Boolean) : NotificationSettingsViewAction
    data class SwitchNotificationFromMyPartners(val enable: Boolean) : NotificationSettingsViewAction
}