package com.spoonofcode.poa.feature.notification.presentation.type

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class NotificationTypeViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val buttonToAllEnable: Boolean = false,
) : BaseViewState(screenState)