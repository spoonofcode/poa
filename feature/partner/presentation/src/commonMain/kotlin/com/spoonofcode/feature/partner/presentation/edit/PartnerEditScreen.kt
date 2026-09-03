package com.spoonofcode.feature.partner.presentation.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.TextFields
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
        partner.imageLink?.let { Images.ImageLink(imageLink = it) }

        TextFields.Outlined(
            value = partner.name,
            onValueChange = { onAction(PartnerEditViewAction.ChangeName(it)) },
            label = stringResource(resource = Res.string.partner_name),
        )
        TextFields.Outlined(
            value = partner.description.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeDescription(it)) },
            label = stringResource(resource = Res.string.description),
            minLines = 8,
        )
        TextFields.Outlined(
            value = partner.websiteLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeWebsiteLink(it)) },
            label = stringResource(resource = Res.string.website),
        )
        TextFields.Outlined(
            value = partner.instagramLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeInstagramLink(it)) },
            label = stringResource(resource = Res.string.instagram),
        )
        TextFields.Outlined(
            value = partner.facebookLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeFacebookLink(it)) },
            label = stringResource(resource = Res.string.facebook),
        )
        TextFields.Outlined(
            value = partner.youtubeLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeYoutubeLink(it)) },
            label = stringResource(resource = Res.string.youtube),
        )
        TextFields.Outlined(
            value = partner.xLink.orEmpty(),
            onValueChange = { onAction(PartnerEditViewAction.ChangeXLink(it)) },
            label = stringResource(resource = Res.string.x),
        )

        Spacers.VerticalBetweenFields()

        PrimaryButton(
            text = stringResource(resource = Res.string.save_changes),
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
