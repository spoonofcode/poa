package com.spoonofcode.feature.partner.presentation.notification.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.data.ext.toMillis
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.notification.domain.model.Notification
import kotlin.time.Clock

@Immutable
internal data class PartnerNotificationDetailsViewState(
    val notification: Notification? = null
) : BaseViewState(){
    val isNotificationEditable: Boolean
        get() = notification?.deliveryDateTime?.toMillis()!! < Clock.System.now().toEpochMilliseconds()
}