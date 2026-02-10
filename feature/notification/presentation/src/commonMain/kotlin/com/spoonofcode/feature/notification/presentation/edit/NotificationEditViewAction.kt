package com.spoonofcode.feature.notification.presentation.edit

import com.spoonofcode.core.presentation.base.BaseViewAction
import kotlinx.datetime.LocalDateTime

internal sealed interface NotificationEditViewAction : BaseViewAction {
    data class ChangeExpirationDateTime(val startDateTime: LocalDateTime) :
        com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction

    data class ChangeLink(val link: String) :
        com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction
    data class ChangeText(val text: String) :
        com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction
    data class ChangeTitle(val title: String) :
        com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction
    data class InitView(val itemIds: List<String>) :
        com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction
    data object SendNotification :
        com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction
}