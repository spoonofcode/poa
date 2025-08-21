package com.spoonofcode.poa.feature.notification.overview

import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class NotificationOverviewViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val initNotifications: List<Notification> = emptyList(),
//    val filteredNotifications: List<Notification> = emptyList(),
) : BaseViewState(screenState)

