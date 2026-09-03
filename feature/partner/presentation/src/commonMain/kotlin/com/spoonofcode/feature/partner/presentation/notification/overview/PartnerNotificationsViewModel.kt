package com.spoonofcode.feature.partner.presentation.notification.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationsUseCase
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEvent
import com.spoonofcode.feature.partner.presentation.notification.PartnerNotificationEventBus
import com.spoonofcode.feature.partner.presentation.partner_notificaiton_added
import com.spoonofcode.feature.partner.presentation.partner_notificaiton_delted
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

internal class PartnerNotificationsViewModel(
    private val getPartnerNotificationsUseCase: GetPartnerNotificationsUseCase,
    private val partnerNotificationEventBus: PartnerNotificationEventBus,
) : BaseViewModel<PartnerNotificationsViewState, PartnerNotificationsViewAction, Nothing>() {
    init {
        observePartnerNotificationsEvents()
    }

    override val startAction = {
        initView()
    }

    override fun onAction(action: PartnerNotificationsViewAction) {
        when (action) {
            is PartnerNotificationsViewAction.SelectNotification -> selectNotification(
                notificationId = action.notificationId
            )
        }
    }

    private fun observePartnerNotificationsEvents() {
        viewModelScope.launch {
            partnerNotificationEventBus.overviewEvents.collect { event ->
                initView()
                when (event) {
                    PartnerNotificationEvent.PartnerNotificationAdded -> {
                        showSuccessSnackbar(getString(Res.string.partner_notificaiton_added))
                    }

                    PartnerNotificationEvent.PartnerNotificationDeleted -> {
                        showSuccessSnackbar(getString(Res.string.partner_notificaiton_delted))
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
                            partnerNotifications = partnerNotifications.filterNot { notification ->
                                notification.state == NOTIFICATION_CANCELED_STATE
                            },
                        )
                    )
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun selectNotification(notificationId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(
                PartnerModuleRoute.PartnerNotificationDetails(
                    notificationId = notificationId
                )
            )
        }
    }

    companion object {
        private const val NOTIFICATION_CANCELED_STATE = "canceled"
    }
}