package com.spoonofcode.poa.feature.partner.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.SocialIcons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.feature.profile.ProfileViewState
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.links
import com.spoonofcode.poa.resources.partner
import com.spoonofcode.poa.resources.partner_beauty_saute
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

internal class PartnerDetailsScreen(
    private val partnerId: Int,
) : BaseScreen<PartnerDetailsViewModel, PartnerDetailsViewState>() {

    override fun provideTopAppBarTitle() = Res.string.partner

    override fun provideTopBarActions(
        viewModel: PartnerDetailsViewModel,
    ) = listOf(
        TopBarAction(Icons.Default.Edit, "Edit") { viewModel.editPartner() },
        TopBarAction(Icons.Default.Delete, "Delete") { viewModel.deletePartner() }
    )

    override fun provideReloadAction(viewModel: PartnerDetailsViewModel): () -> Unit =
        { viewModel.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerDetailsViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: PartnerDetailsViewModel,
        viewState: PartnerDetailsViewState,
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: PartnerDetailsViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Image(
                painter = painterResource(resource = Res.drawable.partner_beauty_saute),
                contentDescription = "Card image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
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
        PreviewContent(
            content = ContentView(
                viewState = PartnerDetailsViewState(
                    partner = Partner(
                        id = 1,
                        name = "Beauty Saute",
                        description = "Beauty Sautè to więcej niż odzież – to filozofia.",
                        email = "beauty.saute@gmail.com",
                        websiteLink = "https://beautysaute.pl/",
                        instagramLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                        facebookLink = "https://www.facebook.com/profile.php?id=61568636916637",
                        youtubeLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                        xLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                    )
                )
            )
        )
    }
    // endregion
}