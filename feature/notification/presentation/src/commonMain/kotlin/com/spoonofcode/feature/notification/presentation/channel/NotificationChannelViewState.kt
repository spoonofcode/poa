package com.spoonofcode.feature.notification.presentation.channel

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class NotificationChannelViewState(
    val buttonEmailEnable: Boolean = false,
    val buttonTelAndSmsEnable: Boolean = false,
) : BaseViewState()