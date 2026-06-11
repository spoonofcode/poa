package com.spoonofcode.feature.partner.presentation.notification.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.data.ext.toMillis
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import kotlin.time.Clock

@Immutable
internal data class PartnerNotificationDetailsViewState(
    val notification: PartnerNotification? = null
) : BaseViewState() {
    val isNotificationEditable: Boolean
        get() = (notification?.deliveryDateTime?.toMillis() ?: 0) < Clock.System.now().toEpochMilliseconds()
}
