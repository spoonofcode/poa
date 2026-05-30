package com.spoonofcode.feature.notification.presentation.partner

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class PartnerNotificationsViewState(
    val buttonToAllEnable: Boolean = false,
) : BaseViewState()