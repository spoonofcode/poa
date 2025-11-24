package com.spoonofcode.poa.feature.notification.edit

import com.spoonofcode.poa.core.ui.BaseViewAction
import kotlinx.datetime.LocalDateTime

internal sealed interface NotificationEditViewAction: BaseViewAction {
    data class ChangeExpirationDateTime(val startDateTime: LocalDateTime) : NotificationEditViewAction
    data class ChangeLink(val link: String) : NotificationEditViewAction
    data class ChangeText(val text: String) : NotificationEditViewAction
    data class ChangeTitle(val title: String) : NotificationEditViewAction
    data class InitView(val itemIds: List<String>) : NotificationEditViewAction
    data object SendNotification : NotificationEditViewAction
}