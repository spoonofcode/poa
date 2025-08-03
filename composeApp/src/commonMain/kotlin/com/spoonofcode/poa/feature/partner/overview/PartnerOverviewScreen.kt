package com.spoonofcode.poa.feature.partner.overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings.innerElevatedCardPadding
import com.spoonofcode.poa.core.ui.Paddings.spaceBetweenListElements
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.partner_beauty_saute
import com.spoonofcode.poa.resources.partners
import com.spoonofcode.poa.resources.search
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

internal class PartnerOverviewScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.partners,
    override val verticalScrollEnable: Boolean = false,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<PartnerOverviewViewModel, PartnerOverviewViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerOverviewViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: PartnerOverviewViewModel,
        viewState: PartnerOverviewViewState
    ): @Composable ColumnScope.() -> Unit {

        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            changeSearchText = { viewModel.changeSearchText(it) },
            selectPartner = { viewModel.selectPartner(it) },
        )
    }

    @Composable
    private fun ContentView(
        viewState: PartnerOverviewViewState,
        changeSearchText: (String) -> Unit,
        selectPartner: (Int) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.searchText,
                onValueChange = { changeSearchText(it) },
                innerLabel = stringResource(resource = Res.string.search),
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
            ) {
                items(viewState.filteredPartners) { partner ->
                    PartnerItem(
                        item = partner,
                        onClick = { selectPartner(partner.id) }
                    )
                }
            }
        }
    }

    @Composable
    private fun PartnerItem(
        item: Partner,
        onClick: () -> Unit,
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerElevatedCardPadding)
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.partner_beauty_saute),
                    contentDescription = "Card image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

//    // region previews
//    // TODO This functions should be private and with @Preview annotation but now
//    //  Android Studio will be support previews in commonMain
//    @Composable
//    fun InitializedPartnerOverviewScreenPreview() {
//        ContentView(
//            content = ContentView(
//                viewState = PartnerOverviewViewState(),
//            )
//        )
//    }
//    // endregion
}