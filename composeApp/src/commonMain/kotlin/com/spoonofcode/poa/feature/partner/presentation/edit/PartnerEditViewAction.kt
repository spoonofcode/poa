package com.spoonofcode.poa.feature.partner.presentation.edit

import com.spoonofcode.poa.core.ui.base.BaseViewAction

internal sealed interface PartnerEditViewAction: BaseViewAction {
    data class ChangeName(val name: String) :
        com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction
    data class ChangeDescription(val description: String) :
        com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction
    data class ChangeWebsiteLink(val link: String) :
        com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction
    data class ChangeInstagramLink(val link: String) :
        com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction
    data class ChangeFacebookLink(val link: String) :
        com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction
    data class ChangeYoutubeLink(val link: String) :
        com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction
    data class ChangeXLink(val link: String) :
        com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction
    data class InitView(val partnerId: String) :
        com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction
    data object SaveChanges :
        com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditViewAction
}