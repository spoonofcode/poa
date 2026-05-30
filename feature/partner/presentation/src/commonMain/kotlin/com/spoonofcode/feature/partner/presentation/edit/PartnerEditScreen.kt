package com.spoonofcode.feature.partner.presentation.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.core.presentation.ext.koinViewModel
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

internal class PartnerEditScreen: BaseScreen<PartnerEditViewModel, PartnerEditViewState, PartnerEditViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.partner

    override fun provideInitAction(onAction: (PartnerEditViewAction) -> Unit): () -> Unit =
        { onAction(PartnerEditViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerEditViewModel>()

    @Composable
    override fun provideContent(
        viewState: PartnerEditViewState,
        onAction: (PartnerEditViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            val partner = viewState.partner

            if (partner != null) {
                Images.ImageLink(imageLink = viewState.partner.imageLink!!)

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

                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.save_changes),
                    onClick = { onAction(PartnerEditViewAction.SaveChanges) },
                )
            }
        }
    }

    @Preview
    @Composable
    private fun InitializedPartnerEditScreenPreview() {
        PartnerEditScreen(
            partnerId = "1"
        ).PreviewContent(
            PartnerEditViewState(
                // TODO #133 fix previews
//                partner = PartnerMockData.PARTNER_1
            )
        )
    }
}