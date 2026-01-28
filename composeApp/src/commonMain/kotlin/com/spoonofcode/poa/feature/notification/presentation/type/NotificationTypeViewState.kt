package com.spoonofcode.poa.feature.notification.presentation.type

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class NotificationTypeViewState(
    val buttonToAllEnable: Boolean = false,
) : BaseViewState()