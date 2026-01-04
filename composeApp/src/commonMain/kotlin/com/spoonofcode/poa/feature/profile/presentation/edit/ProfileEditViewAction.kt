package com.spoonofcode.poa.feature.profile.presentation.edit

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface ProfileEditViewAction: BaseViewAction {
    data class ChangeCustomLink(val link: String) : ProfileEditViewAction
    data object InitView : ProfileEditViewAction
    data object SaveChanges : ProfileEditViewAction
}