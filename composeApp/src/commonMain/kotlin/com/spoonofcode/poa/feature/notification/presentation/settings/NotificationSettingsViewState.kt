package com.spoonofcode.poa.feature.notification.presentation.settings

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.feature.notification.domain.model.NotificationSettings

@Immutable
internal data class NotificationSettingsViewState(
    val notificationSettings: NotificationSettings = NotificationSettings(),
) : BaseViewState()