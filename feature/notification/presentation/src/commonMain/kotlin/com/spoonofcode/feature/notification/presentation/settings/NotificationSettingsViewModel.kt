package com.spoonofcode.feature.notification.presentation.settings

import androidx.lifecycle.viewModelScope
//import com.spoonofcode.core.firebase.usecase.SubscribeToFirebaseMessagingTopicUseCase
//import com.spoonofcode.core.firebase.usecase.UnsubscribeFromFirebaseMessagingTopicUseCase
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationSettingsUseCase
import com.spoonofcode.feature.notification.domain.usecase.UpdateNotificationSettingsUseCase
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationSettingsViewModel(
    private val getNotificationSettingsUseCase: GetNotificationSettingsUseCase,
    private val updateNotificationSettingsUseCase: UpdateNotificationSettingsUseCase,
) : BaseViewModel<NotificationSettingsViewState, NotificationSettingsViewAction>(
    NotificationSettingsViewState()
) {

    override fun onAction(action: NotificationSettingsViewAction) {
        when (action) {
            NotificationSettingsViewAction.InitView -> initView()
            NotificationSettingsViewAction.SaveChanges -> saveChanges()
            is NotificationSettingsViewAction.SwitchPersonalizedNotificationsFromPartners -> switchPersonalizedNotificationsFromPartners(
                action.enable
            )

            is NotificationSettingsViewAction.SwitchNotificationsForSelectedProductSeries -> switchNotificationsForSelectedProductSeries(
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
            try {
                val notificationSettings = getNotificationSettingsUseCase()
                showContentView {
                    NotificationSettingsViewState(
                        notificationSettings = notificationSettings,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    private fun switchPersonalizedNotificationsFromPartners(enable: Boolean) {
        updateState {
            NotificationSettingsViewState(
                notificationSettings = notificationSettings.copy(
                    isPersonalizedPartnerNotificationsEnabled = enable,
                )
            )
        }
    }

    private fun switchNotificationsForSelectedProductSeries(enable: Boolean) {
        updateState {
            NotificationSettingsViewState(
                notificationSettings = notificationSettings.copy(
                    isProductSeriesNotificationsEnabled = enable,
                )
            )
        }
    }

    private fun saveChanges() {
        viewModelScope.launch {
            showLoadingView()
            updateNotificationSettingsUseCase(
                notificationSettings = currentState().notificationSettings
            ).onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }

    private fun acceptNotificationSettings() {
        viewModelScope.launch {
            updateNotificationSettingsUseCase(
                notificationSettings = NotificationSettings()
            ).onSuccess {
                closeRationaleNotificationDialog()
            }.onFailure {
                closeRationaleNotificationDialog()
                showErrorSnackbar(it)
            }
        }
    }

    private fun showRationaleNotificationDialog() {
        updateState {
            copy(
                isRationaleNotificationDialogVisible = true
            )
        }
    }

    private fun closeRationaleNotificationDialog() {
        updateState {
            copy(
                isRationaleNotificationDialogVisible = false
            )
        }
    }
}