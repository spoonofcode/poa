package com.spoonofcode.poa.feature.notification.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.ui.BaseScreen
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

    override fun provideInitAction(viewModel: NotificationOverviewViewModel?): () -> Unit =
        { viewModel?.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewModel: NotificationOverviewViewModel?,
        viewState: NotificationOverviewViewState
    ): @Composable (ColumnScope.() -> Unit) = ContentView(
        viewState = viewState,
        selectNotification = { viewModel?.selectNotification(it) },
    )

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
    fun NotificationItem(
        item: Notification,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        showChevron: Boolean = true,
    ) {
        ElevatedCard(
            onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = item.text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (showChevron) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(20.dp)
                    )
                }
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