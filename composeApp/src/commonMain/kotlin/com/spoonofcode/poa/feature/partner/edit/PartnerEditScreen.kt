package com.spoonofcode.poa.feature.partner.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
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
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class PartnerEditScreen(
    private val partnerId: String,
) : BaseScreen<PartnerEditViewModel, PartnerEditViewState>() {

    override fun provideTopAppBarTitle() = Res.string.partner

    override fun provideInitAction(viewModel: PartnerEditViewModel?): () -> Unit =
        { viewModel?.onAction(PartnerEditViewAction.InitView(partnerId = partnerId)) }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerEditViewModel>()

    @Composable
    override fun provideContent(
        viewModel: PartnerEditViewModel?,
        viewState: PartnerEditViewState
    ): @Composable ColumnScope.() -> Unit = {
        ContentView(
            viewState = viewState,
            onAction = viewModel!!::onAction,
        )
    }

    @Composable
    private fun ContentView(
        viewState: PartnerEditViewState,
        onAction: (PartnerEditViewAction) -> Unit,
    ) {
        val partner = viewState.partner

        if (partner != null) {
            CoilImage(
                imageModel = { viewState.partner.imageLink },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(4.dp, RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = partner.name,
                onValueChange = { onAction.invoke(PartnerEditViewAction.ChangeName(it)) },
                label = stringResource(resource = Res.string.partner_name),
            )
            TextFields.Outlined(
                value = partner.description.orEmpty(), // Używamy orEmpty() dla bezpieczeństwa
                onValueChange = { onAction.invoke(PartnerEditViewAction.ChangeDescription(it)) },
                label = stringResource(resource = Res.string.description),
            )
            TextFields.Outlined(
                value = partner.websiteLink.orEmpty(),
                onValueChange = { onAction.invoke(PartnerEditViewAction.ChangeWebsiteLink(it)) },
                label = stringResource(resource = Res.string.website),
            )
            TextFields.Outlined(
                value = partner.instagramLink.orEmpty(),
                onValueChange = { onAction.invoke(PartnerEditViewAction.ChangeInstagramLink(it)) },
                label = stringResource(resource = Res.string.instagram),
            )
            TextFields.Outlined(
                value = partner.facebookLink.orEmpty(),
                onValueChange = { onAction.invoke(PartnerEditViewAction.ChangeFacebookLink(it)) },
                label = stringResource(resource = Res.string.facebook),
            )
            TextFields.Outlined(
                value = partner.youtubeLink.orEmpty(),
                onValueChange = { onAction.invoke(PartnerEditViewAction.ChangeYoutubeLink(it)) },
                label = stringResource(resource = Res.string.youtube),
            )
            TextFields.Outlined(
                value = partner.xLink.orEmpty(),
                onValueChange = { onAction.invoke(PartnerEditViewAction.ChangeXLink(it)) },
                label = stringResource(resource = Res.string.x),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.save_changes),
                onClick = { onAction.invoke(PartnerEditViewAction.SaveChanges) },
            )
        }
    }

    @Preview
    @Composable
    private fun InitializedPartnerEditScreenPreview() {
        PartnerEditScreen(partnerId = "1").PreviewContent(
            PartnerEditViewState(partner = PartnerMockData.PARTNER_1)
        )
    }
}