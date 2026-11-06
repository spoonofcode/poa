package com.spoonofcode.feature.notification.presentation.settings

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface NotificationSettingsViewAction : BaseViewAction {
    data object SaveChanges :
        NotificationSettingsViewAction

    data class SwitchAllNotification(
        val enable: Boolean,
    ) : NotificationSettingsViewAction

    data class SwitchNotificationsPersonalized(
        val enable: Boolean,
    ) : NotificationSettingsViewAction

    data class SwitchNotificationsFromPartners(
        val enable: Boolean,
    ) : NotificationSettingsViewAction

    data class SwitchNotificationsMyProductSeries(
        val enable: Boolean,
    ) : NotificationSettingsViewAction

    data object AcceptNotificationSettings : NotificationSettingsViewAction

    data object ShowRationaleNotificationDialog : NotificationSettingsViewAction

    data object CloseRationaleNotificationDialog : NotificationSettingsViewAction
}
