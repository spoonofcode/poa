package com.spoonofcode.feature.partner.presentation.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.ImageLink
import com.spoonofcode.core.presentation.compose.OutlinedTextFieldStandard
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.core.presentation.compose.SpacerVerticalBetweenFields
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.description
import com.spoonofcode.feature.partner.presentation.facebook
import com.spoonofcode.feature.partner.presentation.instagram
import com.spoonofcode.feature.partner.presentation.partner
import com.spoonofcode.feature.partner.presentation.partner_name
import com.spoonofcode.feature.partner.presentation.save_changes
import com.spoonofcode.feature.partner.presentation.website
import com.spoonofcode.feature.partner.presentation.x
import com.spoonofcode.feature.partner.presentation.youtube
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.partner),
)

@Composable
internal fun PartnerEditScreen(
    viewModel: PartnerEditViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
        isViewEnable = { !it.isSaveChangesButtonLoading },
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: PartnerEditViewState,
    onAction: (PartnerEditViewAction) -> Unit,
) {
    val partner = viewState.partner

    if (partner != null) {
        partner.imageLink?.let { ImageLink(imageLink = it) }

        OutlinedTextFieldStandard(
            value = partner.name,
            onValueChange = { onAction(PartnerEditViewAction.ChangeName(it)) },
            label = stringResource(resource = Res.string.partner_name),
            isRequired = true,
        )
        OutlinedTextFieldStandard(
            value = partner.description.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeDescription(it)) },
            label = stringResource(resource = Res.string.description),
            minLines = 8,
        )
        OutlinedTextFieldStandard(
            value = partner.websiteLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeWebsiteLink(it)) },
            label = stringResource(resource = Res.string.website),
        )
        OutlinedTextFieldStandard(
            value = partner.instagramLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeInstagramLink(it)) },
            label = stringResource(resource = Res.string.instagram),
        )
        OutlinedTextFieldStandard(
            value = partner.facebookLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeFacebookLink(it)) },
            label = stringResource(resource = Res.string.facebook),
        )
        OutlinedTextFieldStandard(
            value = partner.youtubeLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeYoutubeLink(it)) },
            label = stringResource(resource = Res.string.youtube),
        )
        OutlinedTextFieldStandard(
            value = partner.xLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeXLink(it)) },
            label = stringResource(resource = Res.string.x),
        )

        SpacerVerticalBetweenFields()

        PrimaryButton(
            text = stringResource(resource = Res.string.save_changes),
            enabled = viewState.isSaveChangesButtonEnabled,
            isLoading = viewState.isSaveChangesButtonLoading,
            onClick = { onAction(PartnerEditViewAction.SaveChanges) },
        )
    }
}

// region previews
@Preview
@Composable
private fun PartnerEditScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            PartnerEditViewState(
                partner = Partner(
                    id = "id",
                    name = "name",
                    partnerCategory = "partner category",
                )
            )
        ),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
