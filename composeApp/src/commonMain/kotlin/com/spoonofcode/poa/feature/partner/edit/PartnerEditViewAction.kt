package com.spoonofcode.poa.feature.partner.edit

sealed interface PartnerEditViewAction {
    data class ChangeName(val name: String) : PartnerEditViewAction
    data class ChangeDescription(val description: String) : PartnerEditViewAction
    data class ChangeWebsiteLink(val link: String) : PartnerEditViewAction
    data class ChangeInstagramLink(val link: String) : PartnerEditViewAction
    data class ChangeFacebookLink(val link: String) : PartnerEditViewAction
    data class ChangeYoutubeLink(val link: String) : PartnerEditViewAction
    data class ChangeXLink(val link: String) : PartnerEditViewAction
    data class InitView(val partnerId: String) : PartnerEditViewAction
    data object SaveChanges : PartnerEditViewAction
}