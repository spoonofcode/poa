package com.spoonofcode.feature.home.presentation

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface HomeViewAction: BaseViewAction {
    data object DailyCheckIn : HomeViewAction
    data object InitView : HomeViewAction
    data object NavigateToNotifications : HomeViewAction
    data object GrantedNotificationSettings : HomeViewAction
    data object ShowRationaleNotificationDialog : HomeViewAction
    data object CloseRationaleNotificationDialog : HomeViewAction
}