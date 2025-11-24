package com.spoonofcode.poa.feature.profile.edit

import com.spoonofcode.poa.core.ui.BaseViewAction

sealed interface ProfileEditViewAction: BaseViewAction {
    data class ChangeCustomLink(val link: String) : ProfileEditViewAction
    data object InitView : ProfileEditViewAction
    data object SaveChanges : ProfileEditViewAction
}