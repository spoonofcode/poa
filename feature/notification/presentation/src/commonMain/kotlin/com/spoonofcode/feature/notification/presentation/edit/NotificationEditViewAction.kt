package com.spoonofcode.feature.notification.presentation.edit

import com.spoonofcode.core.presentation.base.BaseViewAction
import kotlinx.datetime.LocalDateTime

internal sealed interface NotificationEditViewAction : BaseViewAction {
    data class ChangeDeliveryDateTime(val selectedDateTime: LocalDateTime) :
        NotificationEditViewAction

    data class ChangeExpirationDateTime(val selectedDateTime: LocalDateTime) :
        NotificationEditViewAction

    data class ChangeLink(val link: String) : NotificationEditViewAction
    data class ChangeText(val text: String) : NotificationEditViewAction
    data class ChangeTitle(val title: String) : NotificationEditViewAction
    data class InitView(
        val selectedTopics: Set<String>,
        val selectedNames: Set<String>,
    ) : NotificationEditViewAction

    data object SendNotification : NotificationEditViewAction
    data object ConfirmSendNotification : NotificationEditViewAction
    data object CancelSendNotification : NotificationEditViewAction
}