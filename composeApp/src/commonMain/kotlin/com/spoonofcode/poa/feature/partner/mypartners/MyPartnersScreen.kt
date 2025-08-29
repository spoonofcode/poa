package com.spoonofcode.poa.feature.partner.mypartners

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.my_partners
import com.spoonofcode.poa.resources.no_partners_found
import org.jetbrains.compose.resources.stringResource

class MyPartnersScreen : BaseScreen<MyPartnersViewModel, MyPartnersViewState>() {
    override val backNavigationEnable: Boolean = true
    override fun provideTopAppBarTitle() = Res.string.my_partners
    override val verticalScrollEnable: Boolean = false

    override fun provideReloadAction(viewModel: MyPartnersViewModel?): () -> Unit = {
        viewModel?.loadMyPartners()
    }

    @Composable
    override fun provideViewModel() = koinViewModel<MyPartnersViewModel>()

    @Composable
    override fun provideContent(
        viewModel: MyPartnersViewModel?,
        viewState: MyPartnersViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            onPartnerClick = { partnerId -> viewModel?.onPartnerClick(partnerId) }
        )
    }

    @Composable
    private fun ContentView(
        viewState: MyPartnersViewState,
        onPartnerClick: (Int) -> Unit
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            if (viewState.partners.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(stringResource(Res.string.no_partners_found))
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(viewState.partners) { partner ->
                        PartnerListItem(
                            partner = partner,
                            onClick = { onPartnerClick(partner.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PartnerListItem(partner: Partner, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        CoilImage(
            imageModel = { partner.imageLink },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth
            ),
            modifier = Modifier.fillMaxWidth().height(150.dp)
        )

    }
}