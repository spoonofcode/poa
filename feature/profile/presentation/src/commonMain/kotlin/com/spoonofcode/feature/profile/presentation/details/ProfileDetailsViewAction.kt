package com.spoonofcode.feature.profile.presentation.details

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ProfileDetailsViewAction: BaseViewAction {
    data object InitView : ProfileDetailsViewAction
    data object Logout : ProfileDetailsViewAction
    data object ConfirmLogout : ProfileDetailsViewAction
    data object CancelLogout : ProfileDetailsViewAction
    data object NavigateToEditProfile : ProfileDetailsViewAction
    data object NavigateToFAQ : ProfileDetailsViewAction
    data object NavigateToNotificationSettings : ProfileDetailsViewAction
    data object NavigateToPartnerPanel : ProfileDetailsViewAction
    data object NavigateToTAC : ProfileDetailsViewAction
}