package com.spoonofcode.poa.feature.notification.presentation.settings

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.feature.notification.domain.usecase.GetNotificationSettingsUseCase
import com.spoonofcode.poa.feature.notification.domain.usecase.UpdateNotificationSettingsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
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
            is NotificationSettingsViewAction.SwitchNotificationFromAllPartners -> switchNotificationFromAllPartners(
                action.enable
            )

            is NotificationSettingsViewAction.SwitchNotificationFromMyPartners -> switchNotificationFromMyPartners(
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

    private fun switchNotificationFromAllPartners(enable: Boolean) {
        updateState {
            NotificationSettingsViewState(
                notificationSettings = notificationSettings.copy(
                    notificationFromAllPartnersEnable = enable,
                )
            )
        }
    }

    private fun switchNotificationFromMyPartners(enable: Boolean) {
        updateState {
            NotificationSettingsViewState(
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
}