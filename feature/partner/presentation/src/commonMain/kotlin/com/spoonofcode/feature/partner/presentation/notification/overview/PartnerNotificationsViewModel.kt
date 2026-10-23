package com.spoonofcode.feature.partner.presentation.notification.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationsUseCase
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEvent
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEventBus
import com.spoonofcode.feature.partner.presentation.partner_notification_added
import com.spoonofcode.feature.partner.presentation.partner_notification_deleted
import kotlinx.coroutines.launch

internal class PartnerNotificationsViewModel(
    private val getPartnerNotificationsUseCase: GetPartnerNotificationsUseCase,
    private val partnerNotificationEventBus: PartnerNotificationEventBus,
) : BaseViewModel<PartnerNotificationsViewState, PartnerNotificationsViewAction, Nothing>() {
    init {
        observeNotificationEvents()
    }

    override val startAction = {
        initView()
    }

    override fun onAction(action: PartnerNotificationsViewAction) {
        when (action) {
            is PartnerNotificationsViewAction.SelectNotification ->
                selectNotification(
                    notificationId = action.notificationId,
                )
        }
    }

    private fun observeNotificationEvents() {
        viewModelScope.launch {
            partnerNotificationEventBus.overviewEvents.collect { event ->
                when (event) {
                    PartnerNotificationEvent.PartnerNotificationAdded -> {
                        showSuccessSnackbar(UiText.Resource(Res.string.partner_notification_added))
                    }

                    PartnerNotificationEvent.PartnerNotificationDeleted -> {
                        showSuccessSnackbar(UiText.Resource(Res.string.partner_notification_deleted))
                    }

                    else -> {}
                }
            }
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getPartnerNotificationsUseCase()
                .onSuccess { partnerNotifications ->
                    setContentState(
                        PartnerNotificationsViewState(
                            partnerNotifications =
                                partnerNotifications.filterNot { notification ->
                                    notification.state == NOTIFICATION_CANCELED_STATE
                                },
                        ),
                    )
                    showContentView()
                }.onFailure {
                    showErrorView()
                }
        }
    }

    private fun selectNotification(notificationId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(
                PartnerModuleRoute.PartnerNotificationDetails(
                    notificationId = notificationId,
                ),
            )
        }
    }

    companion object {
        private const val NOTIFICATION_CANCELED_STATE = "canceled"
    }
}
