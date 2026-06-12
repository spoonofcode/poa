package com.spoonofcode.feature.partner.presentation.notification.type

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class PartnerNotificationTypeViewState(
    val buttonToAllEnable: Boolean = false,
) : BaseViewState()
