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
) : BaseViewModel<com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState, com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction>(
    _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState()
) {

    override fun onAction(action: com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction) {
        when (action) {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction.InitView -> initView()
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction.SaveChanges -> saveChanges()
            is com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction.SwitchNotificationFromAllPartners -> switchNotificationFromAllPartners(
                action.enable
            )

            is com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction.SwitchNotificationFromMyPartners -> switchNotificationFromMyPartners(
                action.enable
            )
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            try {
                val notificationSettings = getNotificationSettingsUseCase()
                _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
                    _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState(
                        notificationSettings = notificationSettings,
                        screenState = ScreenState.CONTENT,
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
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState(
                notificationSettings = notificationSettings.copy(
                    notificationFromAllPartnersEnable = enable,
                )
            )
        }
    }

    private fun switchNotificationFromMyPartners(enable: Boolean) {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState(
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
                updateNotificationSettingsUseCase.invoke(notificationSettings = _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.currentState().notificationSettings)
                _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.viewModelNavigator.pop()
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorView()
            }
        }
    }

    private fun showLoadingView() {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorView() {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState(
                screenState = ScreenState.ERROR,
            )
        }
    }
}