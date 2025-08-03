package com.spoonofcode.poa.feature.partner.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Carousels
import com.spoonofcode.poa.core.ui.compose.SocialIconsRow
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.link
import com.spoonofcode.poa.resources.links
import com.spoonofcode.poa.resources.partner
import com.spoonofcode.poa.resources.partner_beauty_saute
import com.spoonofcode.poa.resources.products
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Google
import compose.icons.fontawesomeicons.brands.Instagram
import compose.icons.fontawesomeicons.brands.Linkedin
import compose.icons.fontawesomeicons.brands.Twitter
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.time.ExperimentalTime

internal class PartnerDetailsScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.partner,
    val partnerId: Int,
) : BaseScreen<PartnerDetailsViewModel, PartnerDetailsViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerDetailsViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: PartnerDetailsViewModel,
        viewState: PartnerDetailsViewState
    ): @Composable ColumnScope.() -> Unit {

        super.reloadScreen = { viewModel.initView() }

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
            SocialIconsRow()

            Spacers.VerticalBetweenFields()
//            Carousels.CustomCarousel(
//                title = stringResource(resource = Res.string.products),
//                items = getMockedProducts(),
//                onItemClick = { goToMyEvent(it) }
//            )
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun getMockedProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Product 1",
                description = "Description product 1",
                tagId = "04A971E2E51090",
                collectionName = "Collection 1",
                websiteLink = "https://beautysaute.pl/",
                customLink = "https://beautysaute.pl/",
                ownerUser = User(
                    id = 1,
                    firstName = "Bartosz",
                    lastName = "Luczak",
                    email = "bartosz.luczak@gmail.com"
                ),
            ),
            Product(
                id = 2,
                name = "Product 2",
                description = "Description product 2",
                tagId = "04A972E2E52090",
                collectionName = "Collection 2",
                websiteLink = "https://beautysaute.pl/",
                customLink = "https://beautysaute.pl/",
                ownerUser = User(
                    id = 2,
                    firstName = "Bartosz",
                    lastName = "Luczak",
                    email = "bartosz.luczak@gmail.com"
                ),
            ),
            Product(
                id = 3,
                name = "Product 3",
                description = "Description product 3",
                tagId = "04A973E3E53090",
                collectionName = "Collection 3",
                websiteLink = "https://beautysaute.pl/",
                customLink = "https://beautysaute.pl/",
                ownerUser = User(
                    id = 3,
                    firstName = "Bartosz",
                    lastName = "Luczak",
                    email = "bartosz.luczak@gmail.com"
                ),
            ),
        )
    }

//    // region previews
//    // TODO This functions should be private and with @Preview annotation but now
//    //  Android Studio will be support previews in commonMain
//    @Composable
//    fun InitializedPartnerDetailsScreenPreview() {
//        ContentView(
//            content = ContentView(
//                viewState = PartnerDetailsViewState(),
//            )
//        )
//    }
//    // endregion
}