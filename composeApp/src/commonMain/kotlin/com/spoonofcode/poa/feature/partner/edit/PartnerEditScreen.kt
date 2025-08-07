package com.spoonofcode.poa.feature.partner.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.description
import com.spoonofcode.poa.resources.facebook
import com.spoonofcode.poa.resources.instagram
import com.spoonofcode.poa.resources.links
import com.spoonofcode.poa.resources.my_products
import com.spoonofcode.poa.resources.partner_name
import com.spoonofcode.poa.resources.partner
import com.spoonofcode.poa.resources.save_changes
import com.spoonofcode.poa.resources.website
import com.spoonofcode.poa.resources.x
import com.spoonofcode.poa.resources.youtube
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class PartnerEditScreen(
    override val backNavigationEnable: Boolean = false,
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

        LaunchedEffect(Unit) {
            viewModel.initView(partnerId = partnerId)
        }
        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: PartnerEditViewState,
    ): @Composable (ColumnScope.() -> Unit) {

        var name by remember(viewState.partner.name) { mutableStateOf(viewState.partner.name) }
        var description by remember(viewState.partner.description) { mutableStateOf(viewState.partner.description) }
        var websiteLink by remember(viewState.partner.websiteLink) { mutableStateOf(viewState.partner.websiteLink.toString()) }
        var instagramLink by remember(viewState.partner.instagramLink) { mutableStateOf(viewState.partner.instagramLink.toString()) }
        var facebookLink by remember(viewState.partner.facebookLink) { mutableStateOf(viewState.partner.facebookLink.toString()) }
        var youtubeLink by remember(viewState.partner.youtubeLink) { mutableStateOf(viewState.partner.youtubeLink.toString()) }
        var xLink by remember(viewState.partner.xLink) { mutableStateOf(viewState.partner.xLink.toString()) }
        return {
            // Obraz pozostaje bez zmian, pobiera link z viewState, bo nie edytujemy go tutaj
            CoilImage(
                imageModel = { viewState.partner.imageLink },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                // Możesz dodać modifier = Modifier.fillMaxWidth().height(200.dp)
            )

            TextFields.Outlined(
                value = name, // Używamy lokalnego stanu
                onValueChange = { name = it }, // Aktualizujemy lokalny stan
                label = stringResource(resource = Res.string.partner_name),
            )
            TextFields.Outlined(
                value = description, // Używamy lokalnego stanu
                onValueChange = { description = it }, // Aktualizujemy lokalny stan
                label = stringResource(resource = Res.string.description),
            )
            TextFields.Outlined(
                value = websiteLink, // Używamy lokalnego stanu
                onValueChange = { websiteLink = it }, // Aktualizujemy lokalny stan
                label = stringResource(resource = Res.string.website),
            )
            TextFields.Outlined(
                value = instagramLink, // Używamy lokalnego stanu
                onValueChange = { instagramLink = it }, // Aktualizujemy lokalny stan
                label = stringResource(resource = Res.string.instagram),
            )
            TextFields.Outlined(
                value = facebookLink, // Używamy lokalnego stanu
                onValueChange = { facebookLink = it }, // Aktualizujemy lokalny stan
                label = stringResource(resource = Res.string.facebook),
            )
            TextFields.Outlined(
                value = youtubeLink, // Używamy lokalnego stanu
                onValueChange = { youtubeLink = it }, // Aktualizujemy lokalny stan
                label = stringResource(resource = Res.string.youtube),
            )
            TextFields.Outlined(
                value = xLink, // Używamy lokalnego stanu
                onValueChange = { xLink = it }, // Aktualizujemy lokalny stan
                label = stringResource(resource = Res.string.x),
            )
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.save_changes),
                onClick = {}
            )

        }
    }

    // region previews
    @Preview
    @Composable
    fun InitializedPartnerEditScreenPreview() {
        PreviewContent(
            content = ContentView(
                viewState = PartnerEditViewState(
                    partner = PartnerMockData.PARTNER_1
                )
            )
        )
    }
    // endregion
}