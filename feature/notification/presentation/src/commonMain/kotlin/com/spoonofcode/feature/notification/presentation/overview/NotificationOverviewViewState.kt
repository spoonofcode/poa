package com.spoonofcode.feature.notification.presentation.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.notification.domain.model.Notification

@Immutable
internal data class NotificationOverviewViewState(
    val notifications: List<Notification> = emptyList(),
) : BaseViewState()

