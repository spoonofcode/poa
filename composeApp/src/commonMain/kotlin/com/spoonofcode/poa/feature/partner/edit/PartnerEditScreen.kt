package com.spoonofcode.poa.feature.partner.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
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
    override val backNavigationEnable: Boolean = true,
    private val partnerId: Int,
) : BaseScreen<PartnerEditViewModel, PartnerEditViewState>() {

    override fun provideTopAppBarTitle() = Res.string.partner

    override fun provideReloadAction(viewModel: PartnerEditViewModel): () -> Unit =
        { viewModel.initView(partnerId = partnerId) }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerEditViewModel>()

    @Composable
    override fun provideContent(
        viewModel: PartnerEditViewModel?,
        viewState: PartnerEditViewState
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(partnerId) {
            viewModel?.initView(partnerId = partnerId)
        }

        return {
            ContentView(
                viewState = viewState,
                onNameChange = { viewModel?.changeName(it) },
                onDescriptionChange = { viewModel?.changeDescription(it) },
                onWebsiteLinkChange = { viewModel?.changeWebsiteLink(it) },
                onInstagramLinkChange = { viewModel?.changeInstagramLink(it) },
                onFacebookLinkChange = { viewModel?.changeFacebookLink(it) },
                onYoutubeLinkChange = { viewModel?.changeYoutubeLink(it) },
                onXLinkChange = { viewModel?.changeXLink(it) },
                onSaveChanges = { viewModel?.saveChanges() },
            )
        }
    }

    @Composable
    private fun ContentView(
        viewState: PartnerEditViewState,
        onNameChange: (String) -> Unit,
        onDescriptionChange: (String) -> Unit,
        onWebsiteLinkChange: (String) -> Unit,
        onInstagramLinkChange: (String) -> Unit,
        onFacebookLinkChange: (String) -> Unit,
        onYoutubeLinkChange: (String) -> Unit,
        onXLinkChange: (String) -> Unit,
        onSaveChanges: () -> Unit,
    ) {
        val partner = viewState.partner

        CoilImage(
            imageModel = { partner.imageLink },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        )

        TextFields.Outlined(
            value = partner.name,
            onValueChange = onNameChange,
            label = stringResource(resource = Res.string.partner_name),
        )
        TextFields.Outlined(
            value = partner.description,
            onValueChange = onDescriptionChange,
            label = stringResource(resource = Res.string.description),
        )
        TextFields.Outlined(
            value = partner.websiteLink.orEmpty(),
            onValueChange = onWebsiteLinkChange,
            label = stringResource(resource = Res.string.website),
        )
        TextFields.Outlined(
            value = partner.instagramLink.orEmpty(),
            onValueChange = onInstagramLinkChange,
            label = stringResource(resource = Res.string.instagram),
        )
        TextFields.Outlined(
            value = partner.facebookLink.orEmpty(),
            onValueChange = onFacebookLinkChange,
            label = stringResource(resource = Res.string.facebook),
        )
        TextFields.Outlined(
            value = partner.youtubeLink.orEmpty(),
            onValueChange = onYoutubeLinkChange,
            label = stringResource(resource = Res.string.youtube),
        )
        TextFields.Outlined(
            value = partner.xLink.orEmpty(),
            onValueChange = onXLinkChange,
            label = stringResource(resource = Res.string.x),
        )

        Buttons.PrimaryButton(
            text = stringResource(resource = Res.string.save_changes),
            onClick = { onSaveChanges() },
        )
    }
}

// region previews
@Preview
@Composable
fun PartnerEditScreenContentPreview() {
    PartnerEditScreen(partnerId = 1).PreviewContent(
        viewState = PartnerEditViewState(),
    )
}
// endregion