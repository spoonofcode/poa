package com.spoonofcode.feature.partner.presentation.notification.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.data.ext.plus
import com.spoonofcode.core.data.utils.LocalDateTimeUtils
import com.spoonofcode.core.domain.ext.isValidHttpsUrl
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime

@Immutable
internal data class PartnerNotificationEditViewState(
    val notificationId: String? = null,
    val notificationType: PartnerNotificationType,
    val selectedTopics: Set<String>,
    val selectedNames: Set<String>,
    val title: String = "",
    val text: String = "",
    val link: String = "",
    val minDeliveryDateTime: LocalDateTime =
        LocalDateTimeUtils
            .now()
            .plus(FIVE_MINUTES, DateTimeUnit.MINUTE),
    val maxDeliveryDateTime: LocalDateTime =
        LocalDateTimeUtils
            .now()
            .plus(TWENTY_NINE_DAYS, DateTimeUnit.DAY),
    val deliveryDateTime: LocalDateTime =
        LocalDateTimeUtils
            .now()
            .plus(FIVE_MINUTES, DateTimeUnit.MINUTE),
    val expirationDateTime: LocalDateTime =
        LocalDateTimeUtils
            .now()
            .plus(ONE_DAY, DateTimeUnit.DAY),
    val minExpirationDateTime: LocalDateTime =
        LocalDateTimeUtils
            .now()
            .plus(ONE_DAY, DateTimeUnit.DAY),
    val isSendNotificationDialogVisible: Boolean = false,
    val isSendNotificationButtonLoading: Boolean = false,
    val isExpirationDateTimeEnable: Boolean = false,
) : BaseViewState() {
    val isViewEnable: Boolean
        get() = !isSendNotificationButtonLoading

    val isLinkValid: Boolean
        get() = link.isEmpty() || link.isValidHttpsUrl()

    val isSendNotificationEnabled: Boolean
        get() = title.isNotEmpty() && text.isNotEmpty() && isLinkValid

    companion object {
        private const val FIVE_MINUTES = 5
        private const val ONE_DAY = 1
        private const val TWENTY_NINE_DAYS = 29
    }
}
