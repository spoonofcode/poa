package com.spoonofcode.poa.feature.partner.details

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.SocialIcons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.links
import com.spoonofcode.poa.resources.partner
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class PartnerDetailsScreen(
    private val partnerId: Int,
) : BaseScreen<PartnerDetailsViewModel, PartnerDetailsViewState>() {

    override fun provideTopAppBarTitle() = Res.string.partner

    override fun provideTopBarActions(
        viewModel: PartnerDetailsViewModel?,
    ) = listOf(
        TopBarAction(Icons.Default.Edit, "Edit") { viewModel?.editPartner() },
        TopBarAction(Icons.Default.Delete, "Delete") { viewModel?.deletePartner() }
    )

    override fun provideReloadAction(viewModel: PartnerDetailsViewModel): () -> Unit =
        { viewModel.initView(partnerId = partnerId) }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerDetailsViewModel>()

    @Composable
    override fun provideContent(
        viewModel: PartnerDetailsViewModel?,
        viewState: PartnerDetailsViewState,
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel?.initView(partnerId = partnerId)
        }

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: PartnerDetailsViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            CoilImage(
                imageModel = { viewState.partner.imageLink }, // loading a network image or local resource using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
            Spacers.VerticalBetweenFields()
            Texts.BM(viewState.partner.description)
            Spacers.VerticalBetweenFields()
            Texts.BM(stringResource(Res.string.links))
            SocialIcons(
                websiteUrl = viewState.partner.websiteLink,
                instagramUrl = viewState.partner.instagramLink,
                facebookUrl = viewState.partner.facebookLink,
                youtubeUrl = viewState.partner.youtubeLink,
                xUrl = viewState.partner.xLink,
            )

            Spacers.VerticalBetweenFields()
//            Carousels.CustomCarousel(
//                title = stringResource(resource = Res.string.products),
//                items = getMockedProducts(),
//                onItemClick = { goToMyEvent(it) }
//            )
        }
    }

    // region previews
    @Preview
    @Composable
    fun InitializedPartnerDetailsScreenPreview() {
        PartnerDetailsScreen(partnerId = 1).PreviewContent(
            viewState = PartnerDetailsViewState(),
        )
    }
    // endregion
}