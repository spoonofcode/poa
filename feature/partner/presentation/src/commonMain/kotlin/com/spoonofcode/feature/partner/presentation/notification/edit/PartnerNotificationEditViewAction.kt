package com.spoonofcode.feature.partner.presentation.notification.edit

import com.spoonofcode.core.presentation.base.BaseViewAction
import kotlinx.datetime.LocalDateTime

internal sealed interface PartnerNotificationEditViewAction : BaseViewAction {
    data class ChangeDeliveryDateTime(val selectedDateTime: LocalDateTime) :
        PartnerNotificationEditViewAction

    data class ChangeExpirationDateTime(val selectedDateTime: LocalDateTime) :
        PartnerNotificationEditViewAction

    data class ChangeLink(val link: String) : PartnerNotificationEditViewAction
    data class ChangeText(val text: String) : PartnerNotificationEditViewAction
    data class ChangeTitle(val title: String) : PartnerNotificationEditViewAction
    data class InitView(
        val notificationId: String? = null,
        val selectedTopics: Set<String>,
        val selectedNames: Set<String>,
    ) : PartnerNotificationEditViewAction

    data object SendNotification : PartnerNotificationEditViewAction
    data object ConfirmSendNotification : PartnerNotificationEditViewAction
    data object CancelSendNotification : PartnerNotificationEditViewAction
}
