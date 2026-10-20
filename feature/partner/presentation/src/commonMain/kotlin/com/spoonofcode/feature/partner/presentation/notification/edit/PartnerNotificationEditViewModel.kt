package com.spoonofcode.feature.partner.presentation.notification.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.usecase.CreatePartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.UpdatePartnerNotificationUseCase
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEvent
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEventBus
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

data class PartnerNotificationEditArgs(
    val notificationId: String?,
    val selectedTopics: Set<String>,
    val selectedNames: Set<String>,
)

internal class PartnerNotificationEditViewModel(
    private val args: PartnerNotificationEditArgs,
    private val createPartnerNotificationUseCase: CreatePartnerNotificationUseCase,
    private val getPartnerNotificationUseCase: GetPartnerNotificationUseCase,
    private val updatePartnerNotificationUseCase: UpdatePartnerNotificationUseCase,
    private val partnerNotificationEventBus: PartnerNotificationEventBus,
) : BaseViewModel<PartnerNotificationEditViewState, PartnerNotificationEditViewAction, Nothing>() {
    override val startAction = {
        initView(
            notificationId = args.notificationId,
            selectedTopics = args.selectedTopics,
            selectedNames = args.selectedNames,
        )
    }

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
                        setContentState(
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
                        )
                        showContentView()
                    }
                    .onFailure {
                        showErrorView()
                    }
            }
        } else {
            setContentState(
                PartnerNotificationEditViewState(
                    notificationType = getNotificationType(selectedTopics),
                    selectedTopics = selectedTopics,
                    selectedNames = selectedNames,
                )
            )
            showContentView()
        }
    }

    private fun getNotificationType(selectedTopics: Set<String>): PartnerNotificationType {
        val firstTopic = selectedTopics.firstOrNull() ?: return PartnerNotificationType.global
        val typePrefix = firstTopic.substringBefore("_")
        return PartnerNotificationType.fromString(typePrefix) ?: PartnerNotificationType.global
    }

    private fun changeTitle(title: String) {
        updateContentState { copy(title = title) }
    }

    private fun changeText(text: String) {
        updateContentState { copy(text = text) }
    }

    private fun changeDeliveryDateTime(selectedDateTime: LocalDateTime) {
        updateContentState { copy(deliveryDateTime = selectedDateTime) }
    }

    private fun changeExpirationDateTime(selectedDateTime: LocalDateTime) {
        updateContentState { copy(expirationDateTime = selectedDateTime) }
    }

    private fun changeLink(link: String) {
        updateContentState { copy(link = link) }
    }

    private fun sendNotification() {
        updateContentState { copy(isSendNotificationDialogVisible = true) }
    }

    private fun cancelSendNotification() {
        updateContentState { copy(isSendNotificationDialogVisible = false) }
    }

    private fun confirmSendNotification() {
        updateContentState {
            copy(
                isSendNotificationButtonLoading = true,
                isSendNotificationDialogVisible = false,
            )
        }
        viewModelScope.launch {
            val currentState = currentContentState()
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
                    partnerNotificationEventBus.emitDetails(PartnerNotificationEvent.PartnerNotificationUpdated)
                    viewModelNavigator.pop()
                }.onFailure { error ->
                    updateContentState { copy(isSendNotificationButtonLoading = false) }
                    error.message?.let { showErrorSnackbar(UiText.DynamicString(it)) }
                }
            } else {
                createPartnerNotificationUseCase(
                    title = currentState.title,
                    text = currentState.text,
                    link = currentState.link,
                    topics = currentState.selectedTopics.toList(),
                    deliveryDateTime = currentState.deliveryDateTime,
                    expirationDateTime = currentState.expirationDateTime
                ).onSuccess {
                    partnerNotificationEventBus.emitOverview(PartnerNotificationEvent.PartnerNotificationAdded)
                    viewModelNavigator.replaceAll(
                        listOf(
                            ProfileModuleRoute.ProfileDetails,
                            PartnerModuleRoute.PartnerPanel,
                            PartnerModuleRoute.PartnerNotifications,
                        )
                    )
                }.onFailure { error ->
                    updateContentState { copy(isSendNotificationButtonLoading = false) }
                    error.message?.let { showErrorSnackbar(UiText.DynamicString(it)) }
                }
            }
        }
    }
}
