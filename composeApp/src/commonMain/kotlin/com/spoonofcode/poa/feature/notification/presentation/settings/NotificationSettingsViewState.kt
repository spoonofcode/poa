package com.spoonofcode.poa.feature.notification.presentation.settings

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class NotificationSettingsViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val notificationSettings: NotificationSettings = NotificationSettings(),
) : BaseViewState(screenState)