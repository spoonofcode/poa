package com.spoonofcode.feature.notification.presentation.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.notification.domain.usecase.SendNotificationUseCase
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

internal class NotificationEditViewModel(
    private val sendNotificationUseCase: SendNotificationUseCase,
) : BaseViewModel<com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewState, com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction>(
    initialViewState = _root_ide_package_.com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction) {
        when (action) {
            is com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction.ChangeExpirationDateTime -> changeExpirationDateTime(
                action.startDateTime
            )

            is com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction.ChangeLink -> changeLink(action.link)
            is com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction.ChangeText -> changeText(action.text)
            is com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction.ChangeTitle -> changeTitle(action.title)
            is com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction.InitView -> initView(action.itemIds)
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewAction.SendNotification -> sendNotification()
        }
    }

    private fun initView(itemIds: List<String>) {
        updateState {
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewState(
                itemIds = itemIds
            )
        }
    }

    private fun changeTitle(title: String) {
        updateState {
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewState(
                title = title
            )
        }
    }

    private fun changeText(text: String) {
        updateState {
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewState(
                text = text
            )
        }
    }

    private fun changeExpirationDateTime(startDateTime: LocalDateTime) {
        updateState {
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewState(
                expirationDateTime = startDateTime
            )
        }
    }

    private fun changeLink(link: String) {
        updateState {
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewState(
                link = link
            )
        }
    }

    private fun sendNotification() {
        viewModelScope.launch {
            showLoadingView()
            val currentState =
                currentState()
            sendNotificationUseCase(
                title = currentState.title,
                text = currentState.text,
                link = currentState.link,
                topics = currentState.itemIds,
                expirationDateTime = currentState.expirationDateTime
            ).onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure { error ->
                showErrorSnackbar(error)
            }

        }
    }
}