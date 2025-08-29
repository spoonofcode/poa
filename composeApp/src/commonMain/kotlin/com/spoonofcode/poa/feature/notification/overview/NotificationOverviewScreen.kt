package com.spoonofcode.poa.feature.notification.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings.innerElevatedCardPadding
import com.spoonofcode.poa.core.ui.Paddings.spaceBetweenListElements
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.notifications
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<NotificationOverviewViewModel, NotificationOverviewViewState>() {

    override fun provideTopAppBarTitle() = Res.string.notifications

    override fun provideReloadAction(viewModel: NotificationOverviewViewModel?): () -> Unit =
        { viewModel?.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewModel: NotificationOverviewViewModel?,
        viewState: NotificationOverviewViewState
    ): @Composable (ColumnScope.() -> Unit) {
        return ContentView(
            viewState = viewState,
            selectNotification = { viewModel?.selectNotification(it) },
        )
    }

    @Composable
    private fun ContentView(
        viewState: NotificationOverviewViewState,
        selectNotification: (Int) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
            ) {
                items(viewState.initNotifications) { partner ->
                    NotificationItem(
                        item = partner,
                        onClick = { selectNotification(partner.id) }
                    )
                }
            }
        }
    }

    @Composable
    private fun NotificationItem(
        item: Notification,
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
                Texts.BM(item.title)
                Texts.BM(item.text)

//                CoilImage(
//                    imageModel = { item.imageLink }, // loading a network image or local resource using an URL.
//                    imageOptions = ImageOptions(
//                        contentScale = ContentScale.Crop,
//                        alignment = Alignment.Center
//                    )
//                )
            }
        }
    }
}

// region previews
@Preview
@Composable
fun NotificationOverviewScreenContentPreview() {
    NotificationOverviewScreen().PreviewContent(
        viewState = NotificationOverviewViewState(),
    )
}
// endregion