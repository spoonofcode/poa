package com.spoonofcode.feature.notification.presentation.edit

import com.spoonofcode.core.presentation.base.BaseViewAction
import kotlinx.datetime.LocalDateTime

internal sealed interface NotificationEditViewAction : BaseViewAction {
    data class ChangeExpirationDateTime(val startDateTime: LocalDateTime) :
        NotificationEditViewAction

    data class ChangeLink(val link: String) : NotificationEditViewAction
    data class ChangeText(val text: String) : NotificationEditViewAction
    data class ChangeTitle(val title: String) : NotificationEditViewAction
    data class InitView(val itemIds: List<String>) : NotificationEditViewAction
    data object SendNotification : NotificationEditViewAction
    data object ConfirmSendNotification : NotificationEditViewAction
    data object CancelSendNotification : NotificationEditViewAction
}