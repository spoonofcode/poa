package com.spoonofcode.feature.partner.presentation.notification.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.PartnerSendNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.UpdatePartnerNotificationUseCase
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

internal class PartnerNotificationEditViewModel(
    private val sendNotificationUseCase: PartnerSendNotificationUseCase,
    private val getPartnerNotificationUseCase: GetPartnerNotificationUseCase,
    private val updatePartnerNotificationUseCase: UpdatePartnerNotificationUseCase,
) : BaseViewModel<PartnerNotificationEditViewState, PartnerNotificationEditViewAction>(
    initialViewState = PartnerNotificationEditViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: PartnerNotificationEditViewAction) {
        when (action) {
            is PartnerNotificationEditViewAction.ChangeDeliveryDateTime -> changeDeliveryDateTime(
                action.selectedDateTime
            )

            is PartnerNotificationEditViewAction.ChangeExpirationDateTime -> changeExpirationDateTime(
                action.selectedDateTime
            )

            is PartnerNotificationEditViewAction.ChangeLink -> changeLink(action.link)
            is PartnerNotificationEditViewAction.ChangeText -> changeText(action.text)
            is PartnerNotificationEditViewAction.ChangeTitle -> changeTitle(action.title)
            is PartnerNotificationEditViewAction.InitView -> initView(
                action.notificationId,
                action.selectedTopics,
                action.selectedNames
            )

            PartnerNotificationEditViewAction.SendNotification -> sendNotification()
            PartnerNotificationEditViewAction.CancelSendNotification -> cancelSendNotification()
            PartnerNotificationEditViewAction.ConfirmSendNotification -> confirmSendNotification()
        }
    }

    private fun initView(
        notificationId: String? = null,
        selectedTopics: Set<String>,
        selectedNames: Set<String>
    ) {
        showLoadingView()
        if (notificationId != null) {
            viewModelScope.launch {
                getPartnerNotificationUseCase(notificationId = notificationId)
                    .onSuccess { notification ->
                        showContentView {
                            PartnerNotificationEditViewState(
                                notificationId = notificationId,
                                notificationType = getNotificationType(notification.topics.toSet()),
                                selectedTopics = notification.topics.toSet(),
                                selectedNames = selectedNames,
                                title = notification.title,
                                text = notification.text,
                                link = notification.link ?: "",
                                deliveryDateTime = notification.deliveryDateTime,
                                expirationDateTime = notification.expirationDateTime
                            )
                        }
                    }
                    .onFailure {
                        showErrorView()
                    }
            }
        } else {
            showContentView {
                PartnerNotificationEditViewState(
                    notificationType = getNotificationType(selectedTopics),
                    selectedTopics = selectedTopics,
                    selectedNames = selectedNames,
                )
            }
        }
    }

    private fun getNotificationType(selectedTopics: Set<String>): PartnerNotificationType {
        val firstTopic = selectedTopics.firstOrNull() ?: return PartnerNotificationType.global
        val typePrefix = firstTopic.substringBefore("_")
        return PartnerNotificationType.fromString(typePrefix) ?: PartnerNotificationType.global
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

    private fun changeDeliveryDateTime(selectedDateTime: LocalDateTime) {
        updateState {
            copy(
                deliveryDateTime = selectedDateTime
            )
        }
    }

    private fun changeExpirationDateTime(selectedDateTime: LocalDateTime) {
        updateState {
            copy(
                expirationDateTime = selectedDateTime
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
        updateState {
            copy(
                isSendNotificationDialogVisible = true
            )
        }
    }

    private fun cancelSendNotification() {
        updateState {
            copy(
                isSendNotificationDialogVisible = false
            )
        }
    }

    private fun confirmSendNotification() {
        viewModelScope.launch {
            showLoadingView()
            val currentState = currentState()
            if (currentState.notificationId != null) {
                updatePartnerNotificationUseCase(
                    notificationId = currentState.notificationId,
                    title = currentState.title,
                    text = currentState.text,
                    link = currentState.link,
                    topics = currentState.selectedTopics.toList(),
                    deliveryDateTime = currentState.deliveryDateTime,
                    expirationDateTime = currentState.expirationDateTime
                ).onSuccess {
                    viewModelNavigator.pop()
                }.onFailure { error ->
                    showErrorSnackbar(error)
                }
            } else {
                sendNotificationUseCase(
                    title = currentState.title,
                    text = currentState.text,
                    link = currentState.link,
                    topics = currentState.selectedTopics.toList(),
                    deliveryDateTime = currentState.deliveryDateTime,
                    expirationDateTime = currentState.expirationDateTime
                ).onSuccess {
                    viewModelNavigator.pop()
                }.onFailure { error ->
                    showErrorSnackbar(error)
                }
            }
        }
    }
}
