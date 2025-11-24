package com.spoonofcode.poa.feature.home

sealed interface HomeViewAction {
    data object DailyCheckIn : HomeViewAction
    data object GetNumberOfNewNotifications : HomeViewAction
    data object InitView : HomeViewAction
    data object NavigateToNotifications : HomeViewAction
}