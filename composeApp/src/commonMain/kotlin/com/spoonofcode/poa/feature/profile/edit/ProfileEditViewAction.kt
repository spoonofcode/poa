package com.spoonofcode.poa.feature.profile.edit

sealed interface ProfileEditViewAction {
    data class ChangeCustomLink(val link: String) : ProfileEditViewAction
    data object InitView : ProfileEditViewAction
    data object SaveChanges : ProfileEditViewAction
}