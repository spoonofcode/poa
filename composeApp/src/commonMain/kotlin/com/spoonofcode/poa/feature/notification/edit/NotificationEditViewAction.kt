package com.spoonofcode.poa.feature.notification.edit

import kotlinx.datetime.LocalDateTime

sealed interface NotificationEditViewAction {
    data class ChangeExpirationDateTime(val startDateTime: LocalDateTime) : NotificationEditViewAction
    data class ChangeLink(val link: String) : NotificationEditViewAction
    data class ChangeText(val text: String) : NotificationEditViewAction
    data class ChangeTitle(val title: String) : NotificationEditViewAction
    data class InitView(val itemIds: List<String>) : NotificationEditViewAction
    data object SendNotification : NotificationEditViewAction
}