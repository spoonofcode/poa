package com.spoonofcode.poa.feature.notification.settings

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.notification.GetNotificationSettingsUseCase
import com.spoonofcode.poa.core.domain.notification.UpdateNotificationSettingsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationSettingsViewModel(
    private val getNotificationSettingsUseCase: GetNotificationSettingsUseCase,
    private val updateNotificationSettingsUseCase: UpdateNotificationSettingsUseCase,
) : BaseViewModel<NotificationSettingsViewState>(NotificationSettingsViewState()) {

    fun onAction(action: NotificationSettingsViewAction) {
        when (action) {
            NotificationSettingsViewAction.InitView -> initView()
            NotificationSettingsViewAction.SaveChanges -> saveChanges()
            is NotificationSettingsViewAction.SwitchNotificationFromAllPartners -> switchNotificationFromAllPartners(
                action.enable
            )

            is NotificationSettingsViewAction.SwitchNotificationFromMyPartners -> switchNotificationFromMyPartners(
                action.enable
            )
        }
    }

    private fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val notificationSettings = getNotificationSettingsUseCase()
                updateState {
                    copy(
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

    private fun switchNotificationFromAllPartners(enable: Boolean) {
        updateState {
            copy(
                notificationSettings = notificationSettings.copy(
                    notificationFromAllPartnersEnable = enable,
                )
            )
        }
    }

    private fun switchNotificationFromMyPartners(enable: Boolean) {
        updateState {
            copy(
                notificationSettings = notificationSettings.copy(
                    notificationFromMyPartnersEnable = enable,
                )
            )
        }
    }

    private fun saveChanges() {
        viewModelScope.launch {
            showLoadingView()
            try {
                updateNotificationSettingsUseCase.invoke(notificationSettings = currentState().notificationSettings)
                viewModelNavigator.pop()
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorView()
            }
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                screenState = ScreenState.ERROR,
            )
        }
    }
}