package com.spoonofcode.feature.notification.presentation.settings

import androidx.lifecycle.viewModelScope
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationSettingsUseCase
import com.spoonofcode.feature.notification.domain.usecase.UpdateNotificationSettingsUseCase
import com.spoonofcode.core.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationSettingsViewModel(
    private val getNotificationSettingsUseCase: GetNotificationSettingsUseCase,
    private val updateNotificationSettingsUseCase: UpdateNotificationSettingsUseCase,
) : BaseViewModel<com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewState, com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction>(
    _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewState()
) {

    override fun onAction(action: com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction) {
        when (action) {
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction.InitView -> initView()
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction.SaveChanges -> saveChanges()
            is com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction.SwitchNotificationFromAllPartners -> switchNotificationFromAllPartners(
                action.enable
            )

            is com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction.SwitchNotificationFromMyPartners -> switchNotificationFromMyPartners(
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
                    _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewState(
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
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewState(
                notificationSettings = notificationSettings.copy(
                    notificationFromAllPartnersEnable = enable,
                )
            )
        }
    }

    private fun switchNotificationFromMyPartners(enable: Boolean) {
        updateState {
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewState(
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