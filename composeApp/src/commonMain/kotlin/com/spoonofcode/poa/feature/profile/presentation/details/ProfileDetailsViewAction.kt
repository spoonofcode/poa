package com.spoonofcode.poa.feature.profile.presentation.details

import com.spoonofcode.poa.core.ui.base.BaseViewAction

internal sealed interface ProfileDetailsViewAction: BaseViewAction {
    data object InitView : ProfileDetailsViewAction
    data object Logout : ProfileDetailsViewAction
    data object NavigateToEditProfile : ProfileDetailsViewAction
    data object NavigateToFAQ : ProfileDetailsViewAction
    data object NavigateToNotificationSettings : ProfileDetailsViewAction
    data object NavigateToPartnerPanel : ProfileDetailsViewAction
    data object NavigateToTAC : ProfileDetailsViewAction
}