package com.spoonofcode.poa.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.spoonofcode.poa.core.ext.formatedLocalDate
import com.spoonofcode.poa.core.model.old.NFTInfo
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings.innerElevatedCardPadding
import com.spoonofcode.poa.core.ui.Paddings.spaceBetweenListElements
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.filter
import com.spoonofcode.poa.resources.search
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class SearchScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.search,
    override val verticalScrollEnable: Boolean = false,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<SearchViewModel, SearchViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<SearchViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: SearchViewModel,
        viewState: SearchViewState
    ): @Composable ColumnScope.() -> Unit {
        super.topBarActions = listOf(
            TopBarAction(
                icon = Icons.Default.FilterList,
                description = stringResource(resource = Res.string.filter),
                onClick = { viewModel.navigateToFilter() }
            ),
        )

        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            changeSearchText = { viewModel.changeSearchText(it) },
            selectNFTInfo = { viewModel.selectNFTInfo(it) },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SearchViewState,
        changeSearchText: (String) -> Unit,
        selectNFTInfo: (String) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.searchText,
                onValueChange = { changeSearchText(it) },
                innerLabel = stringResource(resource = Res.string.search),
            )

            val entries = remember(viewState.filteredNFTInfos) {
                buildEntries(viewState.filteredNFTInfos)
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
            ) {
                entries.forEach { entry ->
                    when (entry) {
                        is ListEntry.DateHeader -> stickyHeader {
                            DateSeparator(entry.date)
                        }

                        is ListEntry.EventRow -> item(key = entry.nftInfo.tagId) {
                            NFTInfoItem(
                                item = entry.nftInfo,
                                onClick = { selectNFTInfo(entry.nftInfo.tagId) }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun NFTInfoItem(
        item: NFTInfo,
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
                Texts.BLB(
                    text = item.tagId,
                )
                NFTInfoItemRowElement(
                    elementValue = item.nftName,
                    icon = Icons.Default.Paid,
                    contentDescription = "NFT name",
                )

                NFTInfoItemRowElement(
                    elementValue = item.series,
                    icon = Icons.Default.WebStories,
                    contentDescription = "Series",
                )

                NFTInfoItemRowElement(
                    elementValue = item.blockchain,
                    icon = Icons.Default.Schema,
                    contentDescription = "Blockchain",
                )
            }
        }
    }

    private sealed interface ListEntry {
        data class DateHeader(val date: LocalDate) : ListEntry
        data class EventRow(val nftInfo: NFTInfo) : ListEntry
    }

    private fun buildEntries(nftInfos: List<NFTInfo>): List<ListEntry> =
        nftInfos
            .sortedBy { it.timestamp }
            .groupBy { it.timestamp.date }
            .flatMap { (date, sameDayEvents) ->
                listOf(ListEntry.DateHeader(date)) +
                        sameDayEvents.map { ListEntry.EventRow(it) }
            }

    @Composable
    private fun DateSeparator(date: LocalDate) {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            Texts.HSB(
                text = date.formatedLocalDate(),
            )
        }
    }

    @Composable
    private fun NFTInfoItemRowElement(
        elementValue: String,
        icon: ImageVector,
        contentDescription: String,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
            Spacers.HorizontalBetweenFields()
            Texts.BM(
                text = elementValue,
            )
        }
    }
}