package com.spoonofcode.feature.notification.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.notification.domain.model.Notification

@Immutable
internal data class NotificationDetailsViewState(
    val notification: Notification,
) : BaseViewState()