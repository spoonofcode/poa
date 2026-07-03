package com.spoonofcode.feature.partner.presentation.notification.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.data.ext.plus
import com.spoonofcode.core.data.utils.LocalDateTimeUtils
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime

@Immutable
internal data class PartnerNotificationEditViewState(
    val notificationId: String? = null,
    val notificationType: PartnerNotificationType = PartnerNotificationType.global,
    val selectedTopics: Set<String> = emptySet(),
    val selectedNames: Set<String> = emptySet(),
    val title: String = "",
    val text: String = "",
    val link: String = "",
    val minDeliveryDateTime: LocalDateTime = LocalDateTimeUtils.now()
        .plus(FIVE_MINUTES, DateTimeUnit.MINUTE),
    val deliveryDateTime: LocalDateTime = LocalDateTimeUtils.now()
        .plus(FIVE_MINUTES, DateTimeUnit.MINUTE),
    val expirationDateTime: LocalDateTime = LocalDateTimeUtils.now()
        .plus(ONE_DAY, DateTimeUnit.DAY),
    val minExpirationDateTime: LocalDateTime = LocalDateTimeUtils.now()
        .plus(ONE_DAY, DateTimeUnit.DAY),
    val isSendNotificationDialogVisible: Boolean = false,
    val isExpirationDateTimeEnable: Boolean = false,
) : BaseViewState() {
    companion object {
        private const val FIVE_MINUTES = 5
        private const val ONE_DAY = 1
    }
}
