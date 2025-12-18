package com.spoonofcode.poa.feature.notification.presentation.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.feature.notification.domain.usecase.SendNotificationUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

internal class NotificationEditViewModel(
    private val sendNotificationUseCase: SendNotificationUseCase,
) : BaseViewModel<com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState, com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction>(
    _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState()
) {

    override fun onAction(action: com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction) {
        when (action) {
            is com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeExpirationDateTime -> changeExpirationDateTime(
                action.startDateTime
            )

            is com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeLink -> changeLink(action.link)
            is com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeText -> changeText(action.text)
            is com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeTitle -> changeTitle(action.title)
            is com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.InitView -> initView(action.itemIds)
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.SendNotification -> sendNotification()
        }
    }

    private fun initView(itemIds: List<String>) {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
                itemIds = itemIds
            )
        }
    }

    private fun changeTitle(title: String) {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
                title = title
            )
        }
    }

    private fun changeText(text: String) {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
                text = text
            )
        }
    }

    private fun changeExpirationDateTime(startDateTime: LocalDateTime) {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
                expirationDateTime = startDateTime
            )
        }
    }

    private fun changeLink(link: String) {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
                link = link
            )
        }
    }

    private fun sendNotification() {
        viewModelScope.launch {
            showLoadingView()
            val currentState =
                _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.currentState()
            sendNotificationUseCase(
                title = currentState.title,
                text = currentState.text,
                link = currentState.link,
                topics = currentState.itemIds,
                expirationDateTime = currentState.expirationDateTime
            ).onSuccess {
                _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure { error ->
                showErrorSnackbar(error)
            }

        }
    }

    private fun showLoadingView() {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorSnackbar(e: Throwable) {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.showSnackbar(
            SnackbarEvent.Error(
                message = "ERROR: $e"
            )
        )
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
                screenState = ScreenState.CONTENT
            )
        }
    }
}