package com.spoonofcode.poa.feature.home

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface HomeViewAction: BaseViewAction {
    data object DailyCheckIn : HomeViewAction
    data object GetNumberOfNewNotifications : HomeViewAction
    data object InitView : HomeViewAction
    data object NavigateToNotifications : HomeViewAction
}