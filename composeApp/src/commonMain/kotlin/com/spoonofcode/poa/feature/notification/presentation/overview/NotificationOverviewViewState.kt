package com.spoonofcode.poa.feature.notification.presentation.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.notification.domain.model.Notification
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class NotificationOverviewViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val notifications: List<Notification> = emptyList(),
) : BaseViewState(screenState)

