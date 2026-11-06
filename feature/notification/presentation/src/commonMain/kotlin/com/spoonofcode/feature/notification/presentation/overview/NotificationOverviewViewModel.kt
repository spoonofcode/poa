package com.spoonofcode.feature.notification.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationsUseCase
import com.spoonofcode.feature.notification.presentation.NotificationEvent
import com.spoonofcode.feature.notification.presentation.NotificationEventBus
import kotlinx.coroutines.launch

internal class NotificationOverviewViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val notificationEventBus: NotificationEventBus,
) : BaseViewModel<NotificationOverviewViewState, NotificationOverviewViewAction, Nothing>() {
    init {
        observeNotificationEvents()
    }

    override val startAction = {
        initView()
    }

    override fun onAction(action: NotificationOverviewViewAction) {
        when (action) {
            is NotificationOverviewViewAction.SelectNotification ->
                selectNotification(
                    notificationId = action.notificationId,
                )
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getNotificationsUseCase()
                .onSuccess {
                    setContentState(
                        NotificationOverviewViewState(
                            notifications = it,
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
                NotificationModuleRoute.NotificationDetails(notificationId = notificationId),
            )
        }
    }

    private fun observeNotificationEvents() {
        viewModelScope.launch {
            notificationEventBus.overviewEvents.collect { event ->
                if (event is NotificationEvent.NotificationRead) {
                    initView()
                }
            }
        }
    }
}
