package com.spoonofcode.poa.feature.notification.details

import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class NotificationDetailsViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val notification: Notification? = null
) : BaseViewState(screenState)