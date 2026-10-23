package com.spoonofcode.feature.partner.presentation.edit

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface PartnerEditViewAction : BaseViewAction {
    data class ChangeName(
        val name: String,
    ) : PartnerEditViewAction

    data class ChangeDescription(
        val description: String,
    ) : PartnerEditViewAction

    data class ChangeWebsiteLink(
        val link: String,
    ) : PartnerEditViewAction

    data class ChangeInstagramLink(
        val link: String,
    ) : PartnerEditViewAction

    data class ChangeFacebookLink(
        val link: String,
    ) : PartnerEditViewAction

    data class ChangeYoutubeLink(
        val link: String,
    ) : PartnerEditViewAction

    data class ChangeXLink(
        val link: String,
    ) : PartnerEditViewAction

    data object SaveChanges : PartnerEditViewAction
}
