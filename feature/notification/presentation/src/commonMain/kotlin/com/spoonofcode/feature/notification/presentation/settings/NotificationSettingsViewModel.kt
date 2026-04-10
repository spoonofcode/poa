package com.spoonofcode.feature.notification.presentation.settings

import androidx.lifecycle.viewModelScope
//import com.spoonofcode.core.firebase.usecase.SubscribeToFirebaseMessagingTopicUseCase
//import com.spoonofcode.core.firebase.usecase.UnsubscribeFromFirebaseMessagingTopicUseCase
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationSettingsUseCase
import com.spoonofcode.feature.notification.domain.usecase.UpdateNotificationSettingsUseCase
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationSettingsViewModel(
    private val getNotificationSettingsUseCase: GetNotificationSettingsUseCase,
    private val updateNotificationSettingsUseCase: UpdateNotificationSettingsUseCase,
//    private val subscribeToFirebaseTopicsUseCase: SubscribeToFirebaseMessagingTopicUseCase,
//    private val unsubscribeFromFirebaseMessagingTopicUseCase: UnsubscribeFromFirebaseMessagingTopicUseCase,
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
            try {
                updateNotificationSettingsUseCase(
                    notificationSettings = currentState().notificationSettings
                )

                viewModelNavigator.pop()
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorView()
            }
        }
    }
}