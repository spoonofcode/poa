package com.spoonofcode.poa.feature.notification.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.notification.domain.model.Notification
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class NotificationDetailsViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val notification: Notification? = null
) : BaseViewState(screenState)