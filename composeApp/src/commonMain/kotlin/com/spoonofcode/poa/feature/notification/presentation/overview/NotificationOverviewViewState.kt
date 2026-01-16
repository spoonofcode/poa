package com.spoonofcode.poa.feature.notification.presentation.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.base.BaseViewState
import com.spoonofcode.poa.feature.notification.domain.model.Notification

@Immutable
internal data class NotificationOverviewViewState(
    val notifications: List<Notification> = emptyList(),
) : BaseViewState()

