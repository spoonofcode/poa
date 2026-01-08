package com.spoonofcode.poa.feature.notification.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.feature.notification.domain.model.Notification

@Immutable
internal data class NotificationDetailsViewState(
    val notification: Notification? = null
) : BaseViewState()