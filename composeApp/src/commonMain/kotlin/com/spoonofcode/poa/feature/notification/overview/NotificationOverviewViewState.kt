package com.spoonofcode.poa.feature.notification.overview

import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class NotificationOverviewViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val initNotifications: List<Notification> = emptyList(),
//    val filteredNotifications: List<Notification> = emptyList(),
) : BaseViewState()

