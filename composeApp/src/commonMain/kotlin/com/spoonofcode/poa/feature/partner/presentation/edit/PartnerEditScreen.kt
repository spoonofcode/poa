package com.spoonofcode.poa.feature.partner.presentation.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Images
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.description
import com.spoonofcode.poa.resources.facebook
import com.spoonofcode.poa.resources.instagram
import com.spoonofcode.poa.resources.partner
import com.spoonofcode.poa.resources.partner_name
import com.spoonofcode.poa.resources.save_changes
import com.spoonofcode.poa.resources.website
import com.spoonofcode.poa.resources.x
import com.spoonofcode.poa.resources.youtube
import org.jetbrains.compose.resources.stringResource

internal class PartnerEditScreen(
    private val partnerId: String,
) : BaseScreen<PartnerEditViewModel, PartnerEditViewState, PartnerEditViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.partner

    override fun provideInitAction(onAction: (PartnerEditViewAction) -> Unit): () -> Unit =
        { onAction(PartnerEditViewAction.InitView(partnerId = partnerId)) }

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
                partner = PartnerMockData.PARTNER_1
            )
        )
    }
}