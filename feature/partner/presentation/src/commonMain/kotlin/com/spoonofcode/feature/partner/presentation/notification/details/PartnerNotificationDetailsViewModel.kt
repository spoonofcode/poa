package com.spoonofcode.feature.partner.presentation.notification.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.usecase.DeletePartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationUseCase
import kotlinx.coroutines.launch

internal class PartnerNotificationDetailsViewModel(
    private val getPartnerNotificationUseCase: GetPartnerNotificationUseCase,
    private val deletePartnerNotificationUseCase: DeletePartnerNotificationUseCase,
) : BaseViewModel<PartnerNotificationDetailsViewState, PartnerNotificationDetailsViewAction>(
    PartnerNotificationDetailsViewState()
) {

    override fun onAction(action: PartnerNotificationDetailsViewAction) {
        when (action) {
            is PartnerNotificationDetailsViewAction.InitView -> initView(action.notificationId)
            PartnerNotificationDetailsViewAction.DeleteNotification -> deleteNotification()
            PartnerNotificationDetailsViewAction.EditNotification -> editNotification()
            PartnerNotificationDetailsViewAction.CancelDeleteNotification -> cancelDeleteNotification()
            PartnerNotificationDetailsViewAction.ConfirmDeleteNotification -> confirmDeleteNotification()
        }
    }

    private fun initView(notificationId: String) {
        showLoadingView()
        viewModelScope.launch {
            getPartnerNotificationUseCase(notificationId = notificationId)
                .onSuccess { notification ->
                    showContentView {
                        PartnerNotificationDetailsViewState(
                            notification = notification,
                            notificationType = getNotificationType(notification.topics.toSet()),
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun getNotificationType(selectedTopics: Set<String>): PartnerNotificationType {
        val firstTopic = selectedTopics.firstOrNull() ?: return PartnerNotificationType.global
        val typePrefix = firstTopic.substringBefore("_")
        return PartnerNotificationType.fromString(typePrefix) ?: PartnerNotificationType.global
    }

    private fun deleteNotification() {
        updateState {
            copy(
                isDeleteNotificationDialogVisible = true,
            )
        }
        viewModelScope.launch {
            deletePartnerNotificationUseCase.invoke(notificationId = currentState().notification!!.id)
        }
    }

    private fun cancelDeleteNotification() {
        updateState {
            copy(
                isDeleteNotificationDialogVisible = false,
            )
        }
    }

    private fun confirmDeleteNotification() {
        showLoadingView()
        viewModelScope.launch {
            val notification = requireNotNull(currentState().notification)
            deletePartnerNotificationUseCase(
                notificationId = notification.id,
            ).onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }

    private fun editNotification() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(
                    PartnerModuleRoute.PartnerNotificationEdit(
                        notificationId = currentState().notification!!.id
                    )
                )
            )
        }
    }
}
