package com.spoonofcode.feature.notification.presentation.settings

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationSettingsViewAction : BaseViewAction {
    data object InitView :
        NotificationSettingsViewAction
    data object SaveChanges :
        NotificationSettingsViewAction
    data class SwitchPersonalizedNotificationsFromPartners(val enable: Boolean) :
        NotificationSettingsViewAction

    data class SwitchNotificationsForSelectedProductSeries(val enable: Boolean) :
        NotificationSettingsViewAction

    data object AcceptNotificationSettings : NotificationSettingsViewAction
    data object ShowRationaleNotificationDialog : NotificationSettingsViewAction
    data object CloseRationaleNotificationDialog : NotificationSettingsViewAction
}