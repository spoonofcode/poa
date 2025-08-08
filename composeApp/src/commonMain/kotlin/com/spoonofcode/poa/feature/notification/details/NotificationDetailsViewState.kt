package com.spoonofcode.poa.feature.notification.details

import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class NotificationDetailsViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val notification: Notification? = null
) : BaseViewState()