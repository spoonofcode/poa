package com.spoonofcode.feature.notification.presentation.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.data.utils.TimeZoneUtils
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.AppModule
import com.spoonofcode.feature.notification.domain.usecase.SendNotificationUseCase
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime

internal class NotificationEditViewModel(
    private val sendNotificationUseCase: SendNotificationUseCase,
) : BaseViewModel<NotificationEditViewState, NotificationEditViewAction>(
    initialViewState = NotificationEditViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: NotificationEditViewAction) {
        when (action) {
            is NotificationEditViewAction.ChangeExpirationDateTime -> changeExpirationDateTime(
                action.startDateTime
            )

            is NotificationEditViewAction.ChangeLink -> changeLink(action.link)
            is NotificationEditViewAction.ChangeText -> changeText(action.text)
            is NotificationEditViewAction.ChangeTitle -> changeTitle(action.title)
            is NotificationEditViewAction.InitView -> initView(action.itemIds)
            NotificationEditViewAction.SendNotification -> sendNotification()
        }
    }

    private fun initView(itemIds: List<String>) {
        updateState {
            NotificationEditViewState(
                itemIds = itemIds
            )
        }
    }

    private fun changeTitle(title: String) {
        updateState {
            copy(
                title = title
            )
        }
    }

    private fun changeText(text: String) {
        updateState {
            copy(
                text = text
            )
        }
    }

    private fun changeExpirationDateTime(expirationDateTime: LocalDateTime) {
        updateState {
            copy(
                expirationDateTime = expirationDateTime
            )
        }
    }

    private fun changeLink(link: String) {
        updateState {
            copy(
                link = link
            )
        }
    }

    private fun sendNotification() {
        viewModelScope.launch {
            showLoadingView()
            val currentState = currentState()
            sendNotificationUseCase(
                title = currentState.title,
                text = currentState.text,
                link = currentState.link,
                topic = "global",
//                topics = currentState.itemIds,
                expirationDateTime = currentState.expirationDateTime
            ).onSuccess {
                viewModelNavigator.replaceAll(listOf(routeResolver.resolve(AppModule.MainHostScreen)))
            }.onFailure { error ->
                showErrorSnackbar(error)
            }

        }
    }
}