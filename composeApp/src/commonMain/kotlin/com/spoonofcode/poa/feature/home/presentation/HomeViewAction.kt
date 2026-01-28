package com.spoonofcode.poa.feature.home.presentation

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface HomeViewAction: BaseViewAction {
    data object DailyCheckIn : HomeViewAction
    data object GetNumberOfNewNotifications : HomeViewAction
    data object InitView : HomeViewAction
    data object NavigateToNotifications : HomeViewAction
}