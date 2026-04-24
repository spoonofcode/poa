package com.spoonofcode.feature.notification.presentation.settings

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.core.data.network.NotificationSettings

@Immutable
internal data class NotificationSettingsViewState(
    val notificationSettings: NotificationSettings = NotificationSettings(),
    val isRationaleNotificationDialogVisible: Boolean = false,
) : BaseViewState() {
    val isAllPushNotificationsSettingsEnable: Boolean
        get() = notificationSettings.isNotificationsPersonalizedEnabled &&
                notificationSettings.isNotificationsFromPartnersEnabled &&
                notificationSettings.isNotificationsMyProductSeriesEnabled
}