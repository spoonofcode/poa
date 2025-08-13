package com.spoonofcode.poa.feature.partner.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.*
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
    override fun provideContentView(
        viewModel: PartnerEditViewModel,
        viewState: PartnerEditViewState
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(partnerId) {
            viewModel.initView(partnerId = partnerId)
        }

        return {
            ContentView(
                viewState = viewState,
                onStateChange = viewModel::onStateChange,
                onSaveChanges = viewModel::saveChanges
            )
        }
    }

    @Composable
    private fun ContentView(
        viewState: PartnerEditViewState,
        onStateChange: (Partner) -> Unit,
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
            onValueChange = { onStateChange(partner.copy(name = it)) },
            label = stringResource(resource = Res.string.partner_name),
        )
        TextFields.Outlined(
            value = partner.description,
            onValueChange = { onStateChange(partner.copy(description = it)) },
            label = stringResource(resource = Res.string.description),
        )
        TextFields.Outlined(
            value = partner.websiteLink.orEmpty(),
            onValueChange = { onStateChange(partner.copy(websiteLink = it)) },
            label = stringResource(resource = Res.string.website),
        )
        TextFields.Outlined(
            value = partner.instagramLink.orEmpty(),
            onValueChange = { onStateChange(partner.copy(instagramLink = it)) },
            label = stringResource(resource = Res.string.instagram),
        )
        TextFields.Outlined(
            value = partner.facebookLink.orEmpty(),
            onValueChange = { onStateChange(partner.copy(facebookLink = it)) },
            label = stringResource(resource = Res.string.facebook),
        )
        TextFields.Outlined(
            value = partner.youtubeLink.orEmpty(),
            onValueChange = { onStateChange(partner.copy(youtubeLink = it)) },
            label = stringResource(resource = Res.string.youtube),
        )
        TextFields.Outlined(
            value = partner.xLink.orEmpty(),
            onValueChange = { onStateChange(partner.copy(xLink = it)) },
            label = stringResource(resource = Res.string.x),
        )

        Buttons.PrimaryButton(
            text = stringResource(resource = Res.string.save_changes),
            onClick = {
                if (!viewState.isLoadingView) {
                    onSaveChanges()
                }
            }
        )
    }

    @Preview
    @Composable
    fun InitializedPartnerEditScreenPreview() {
        PreviewContent(
            content = {
                ContentView(
                    viewState = PartnerEditViewState(
                        partner = PartnerMockData.PARTNER_1
                    ),
                    onStateChange = {},
                    onSaveChanges = {}
                )
            }
        )
    }
}