package com.spoonofcode.feature.profile.presentation.edit

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ProfileEditViewAction: BaseViewAction {
    data class ChangeCustomLink(val link: String) : ProfileEditViewAction
    data object InitView : ProfileEditViewAction
    data object SaveChanges : ProfileEditViewAction
}