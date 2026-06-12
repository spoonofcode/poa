package com.spoonofcode.feature.partner.presentation.notification.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.data.ext.plus
import com.spoonofcode.core.data.ext.roundToNextHour
import com.spoonofcode.core.data.utils.LocalDateTimeUtils
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import kotlinx.datetime.LocalDateTime

@Immutable
internal data class PartnerNotificationEditViewState(
    val notificationType: PartnerNotificationType = PartnerNotificationType.global,
    val selectedTopics: Set<String> = emptySet(),
    val selectedNames: Set<String> = emptySet(),
    val title: String = "",
    val text: String = "",
    val link: String = "",
    val minDeliveryDateTime: LocalDateTime = LocalDateTimeUtils.now()
        .plus(TWO_HOURS)
        .roundToNextHour(),
    val deliveryDateTime: LocalDateTime = LocalDateTimeUtils.now()
        .plus(TWO_HOURS)
        .roundToNextHour(),
    val expirationDateTime: LocalDateTime = LocalDateTimeUtils.now()
        .plus(TWENTY_SIX_HOURS)
        .roundToNextHour(),
    val minExpirationDateTime: LocalDateTime = LocalDateTimeUtils.now()
        .plus(TWO_HOURS)
        .roundToNextHour(),
    val isSendNotificationDialogVisible: Boolean = false,
) : BaseViewState() {
    companion object {
        private const val TWENTY_SIX_HOURS = 26
        private const val TWO_HOURS = 2
    }
}
