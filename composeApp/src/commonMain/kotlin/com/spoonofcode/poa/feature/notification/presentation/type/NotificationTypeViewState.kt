package com.spoonofcode.poa.feature.notification.presentation.type

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.base.BaseViewState

@Immutable
internal data class NotificationTypeViewState(
    val buttonToAllEnable: Boolean = false,
) : BaseViewState()