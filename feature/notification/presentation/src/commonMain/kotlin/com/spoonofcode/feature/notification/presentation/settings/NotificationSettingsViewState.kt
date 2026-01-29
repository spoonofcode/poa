package com.spoonofcode.feature.notification.presentation.settings

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.notification.domain.model.NotificationSettings

@Immutable
internal data class NotificationSettingsViewState(
    val notificationSettings: NotificationSettings = NotificationSettings(),
) : BaseViewState()