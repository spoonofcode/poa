package com.spoonofcode.feature.partner.presentation.notification.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.domain.model.Customer
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.usecase.DeletePartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerCustomersUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationUseCase
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEvent
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEventBus
import com.spoonofcode.feature.partner.presentation.partner_notification_updated
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class PartnerNotificationDetailsViewModel(
    private val notificationId: String,
    private val getPartnerNotificationUseCase: GetPartnerNotificationUseCase,
    private val deletePartnerNotificationUseCase: DeletePartnerNotificationUseCase,
    private val getPartnerCustomersUseCase: GetPartnerCustomersUseCase,
    private val partnerNotificationEventBus: PartnerNotificationEventBus,
) : BaseViewModel<PartnerNotificationDetailsViewState, PartnerNotificationDetailsViewAction, Nothing>() {
    init {
        observePartnerNotificationsEvents()
    }

    override val startAction = {
        initView(notificationId = notificationId)
    }

    override fun onAction(action: PartnerNotificationDetailsViewAction) {
        when (action) {
            PartnerNotificationDetailsViewAction.DeleteNotification -> deleteNotification()
            PartnerNotificationDetailsViewAction.EditNotification -> editNotification()
            PartnerNotificationDetailsViewAction.CancelDeleteNotification -> cancelDeleteNotification()
            PartnerNotificationDetailsViewAction.ConfirmDeleteNotification -> confirmDeleteNotification()
        }
    }

    private fun observePartnerNotificationsEvents() {
        viewModelScope.launch {
            partnerNotificationEventBus.detailsEvents.collect { event ->
                if (event is PartnerNotificationEvent.PartnerNotificationUpdated) {
                    initView(notificationId)
                    showSuccessSnackbar(UiText.Resource(Res.string.partner_notification_updated))
                }
            }
        }
    }

    private fun initView(notificationId: String) {
        showLoadingView()
        viewModelScope.launch {
            val notificationDeferred =
                async { getPartnerNotificationUseCase(notificationId = notificationId) }
            val customersDeferred = async { getPartnerCustomersUseCase() }

            val notificationResult = notificationDeferred.await()
            val customersResult = customersDeferred.await()

            if (notificationResult.isSuccess && customersResult.isSuccess) {
                val notification = notificationResult.getOrThrow()
                val customers = customersResult.getOrThrow()

                setContentState(
                    PartnerNotificationDetailsViewState(
                        notification = notification,
                        notificationType = getNotificationType(notification.topics.toSet()),
                        selectedNames = getSelectedNames(notification.topics, customers)
                    )
                )
                showContentView()
            } else {
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
        updateContentState { copy(isDeleteNotificationDialogVisible = true) }
    }

    private fun cancelDeleteNotification() {
        updateContentState { copy(isDeleteNotificationDialogVisible = false) }
    }

    private fun confirmDeleteNotification() {
        updateContentState {
            copy(
                isDeleteNotificationButtonLoading = true,
                isDeleteNotificationDialogVisible = false,
            )
        }
        viewModelScope.launch {
            val notification = currentContentState().notification
            deletePartnerNotificationUseCase(
                notificationId = notification.id,
            ).onSuccess {
                partnerNotificationEventBus.emitOverview(PartnerNotificationEvent.PartnerNotificationDeleted)
                viewModelScope.launch {
                    viewModelNavigator.popUpTo(PartnerModuleRoute.PartnerPanel::class)
                }
            }.onFailure {
                updateContentState { copy(isDeleteNotificationButtonLoading = false) }
                it.message?.let { message -> showErrorSnackbar(message) }
            }
        }
    }

    private fun editNotification() {
        viewModelScope.launch {
            val notification = requireNotNull(currentContentState().notification)
            viewModelNavigator.push(
                PartnerModuleRoute.PartnerNotificationEdit(
                    notificationId = notification.id,
                    selectedTopics = notification.topics.toSet(),
                    selectedNames = currentContentState().selectedNames,
                )
            )
        }
    }

    private fun getSelectedNames(topics: List<String>, customers: List<Customer>): Set<String> {
        val userPrefix = PartnerNotificationType.user.name
        val seriePrefix = PartnerNotificationType.serie.name

        return when {
            topics.any { it.startsWith(userPrefix) } -> {
                val cleanIds = topics
                    .filter { it.startsWith(userPrefix) }
                    .map { it.removePrefix(userPrefix).replace("_", "") }
                    .toSet()

                customers
                    .filter { it.id in cleanIds }
                    .map { it.name }
                    .toSet()
            }

            topics.any { it.startsWith(seriePrefix) } -> {
                topics
                    .filter { it.startsWith(seriePrefix) }
                    .map { it.removePrefix(seriePrefix).replace("_", "") }
                    .toSet()
            }

            else -> emptySet()
        }
    }
}
