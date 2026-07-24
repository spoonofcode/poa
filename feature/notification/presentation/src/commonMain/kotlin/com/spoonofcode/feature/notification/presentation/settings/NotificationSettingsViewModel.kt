package com.spoonofcode.feature.notification.presentation.settings

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationSettingsUseCase
import com.spoonofcode.feature.notification.domain.usecase.UpdateNotificationSettingsUseCase
import kotlinx.coroutines.launch

internal class NotificationSettingsViewModel(
    private val getNotificationSettingsUseCase: GetNotificationSettingsUseCase,
    private val updateNotificationSettingsUseCase: UpdateNotificationSettingsUseCase,
) : BaseViewModel<NotificationSettingsViewState, NotificationSettingsViewAction, Nothing>() {
    override val startAction = {
        initView()
    }

    override fun onAction(action: NotificationSettingsViewAction) {
        when (action) {
            NotificationSettingsViewAction.SaveChanges -> saveChanges()
            is NotificationSettingsViewAction.SwitchAllNotification -> switchAllNotification(
                action.enable
            )

            is NotificationSettingsViewAction.SwitchNotificationsPersonalized -> switchNotificationsPersonalized(
                action.enable
            )

            is NotificationSettingsViewAction.SwitchNotificationsFromPartners -> switchNotificationsFromPartners(
                action.enable
            )

            is NotificationSettingsViewAction.SwitchNotificationsMyProductSeries -> switchNotificationsMyProductSeries(
                action.enable
            )

            NotificationSettingsViewAction.AcceptNotificationSettings -> acceptNotificationSettings()
            NotificationSettingsViewAction.ShowRationaleNotificationDialog -> showRationaleNotificationDialog()
            NotificationSettingsViewAction.CloseRationaleNotificationDialog -> closeRationaleNotificationDialog()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getNotificationSettingsUseCase()
                .onSuccess { notificationSettings ->
                    setContentState(
                        NotificationSettingsViewState(
                            notificationSettings = notificationSettings,
                        )
                    )
                    showContentView()
                }.onFailure {
                    it.message?.let { message -> showErrorSnackbar(message) }
                }
        }
    }

    private fun switchAllNotification(enable: Boolean) {
        updateContentState {
            NotificationSettingsViewState(
                notificationSettings = notificationSettings.copy(
                    isNotificationsPersonalizedEnabled = enable,
                    isNotificationsFromPartnersEnabled = enable,
                    isNotificationsMyProductSeriesEnabled = enable,
                )
            )
        }
    }

    private fun switchNotificationsPersonalized(enable: Boolean) {
        updateContentState {
            NotificationSettingsViewState(
                notificationSettings = notificationSettings.copy(
                    isNotificationsPersonalizedEnabled = enable,
                )
            )
        }
    }

    private fun switchNotificationsFromPartners(enable: Boolean) {
        updateContentState {
            NotificationSettingsViewState(
                notificationSettings = notificationSettings.copy(
                    isNotificationsFromPartnersEnabled = enable,
                )
            )
        }
    }

    private fun switchNotificationsMyProductSeries(enable: Boolean) {
        updateContentState {
            NotificationSettingsViewState(
                notificationSettings = notificationSettings.copy(
                    isNotificationsMyProductSeriesEnabled = enable,
                )
            )
        }
    }

    private fun saveChanges() {
        viewModelScope.launch {
            showLoadingView()
            updateNotificationSettingsUseCase(
                notificationSettings = currentContentState().notificationSettings
            ).onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                it.message?.let { message -> showErrorSnackbar(message) }
            }
        }
    }

    private fun acceptNotificationSettings() {
        closeRationaleNotificationDialog()
    }

    private fun showRationaleNotificationDialog() {
        updateContentState {
            copy(
                isRationaleNotificationDialogVisible = true
            )
        }
    }

    private fun closeRationaleNotificationDialog() {
        updateContentState {
            copy(
                isRationaleNotificationDialogVisible = false
            )
        }
    }
}