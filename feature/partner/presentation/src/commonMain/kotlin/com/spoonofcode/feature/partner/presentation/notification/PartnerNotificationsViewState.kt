package com.spoonofcode.feature.partner.presentation.notification

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.partner.domain.model.PartnerNotification

@Immutable
internal data class PartnerNotificationsViewState(
    val partnerNotifications: List<PartnerNotification> = emptyList(),
) : BaseViewState()