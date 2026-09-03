package com.spoonofcode.feature.partner.presentation.notification.overview

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.data.ext.toMillis
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.ElevatedCard
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.core.presentation.compose.SpacerVerticalBetweenFields
import com.spoonofcode.core.presentation.compose.TextBM
import com.spoonofcode.core.presentation.compose.TextTM
import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.my_notifications
import com.spoonofcode.feature.partner.presentation.tab_already_sent
import com.spoonofcode.feature.partner.presentation.tab_pending
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.my_notifications),
    verticalScrollEnable = false,
    contentPadding = PaddingValues(
        start = Paddings.screenPadding,
        end = Paddings.screenPadding,
    )
)

@Composable
internal fun PartnerNotificationsScreen(
    viewModel: PartnerNotificationsViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: PartnerNotificationsViewState,
    onAction: (PartnerNotificationsViewAction) -> Unit,
) {
    NotificationTabs(
        viewState.partnerNotifications,
        selectNotification = { onAction(PartnerNotificationsViewAction.SelectNotification(it)) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationTabs(
    notifications: List<PartnerNotification>,
    selectNotification: (String) -> Unit,
) {
    val tabs = listOf(
        TabItem(title = stringResource(Res.string.tab_pending)),
        TabItem(title = stringResource(Res.string.tab_already_sent)),
    )

    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex,
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(tab.title) },
                )
            }
        }
        val notificationsPending = notifications.filterNot {
            it.deliveryDateTime.toMillis() < Clock.System.now().toEpochMilliseconds()
        }
        val notificationsAlreadySent = notifications.filter {
            it.deliveryDateTime.toMillis() < Clock.System.now().toEpochMilliseconds()
        }

        when (selectedTabIndex) {
            0 -> NotificationTab(notificationsPending, selectNotification)
            1 -> NotificationTab(notificationsAlreadySent, selectNotification)
        }
    }
}

private data class TabItem(val title: String)

@Composable
private fun NotificationTab(
    notifications: List<PartnerNotification>,
    selectNotification: (String) -> Unit
) {
    SpacerVerticalBetweenFields()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
    ) {
        items(notifications) { notification ->
            NotificationItem(
                item = notification,
                onClick = { selectNotification(notification.id) }
            )
        }
    }
}

@Composable
private fun NotificationItem(
    item: PartnerNotification,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ElevatedCard(
            onClick = onClick
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

                    TextTM(
                        text = item.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    TextBM(
                        text = item.text,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun PartnerNotificationsScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            PartnerNotificationsViewState(
                partnerNotifications = listOf(
                    PartnerNotification(
                        id = "1",
                        title = "Title",
                        text = "Text",
                        deliveryDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                        createdDataTime = LocalDateTime(2023, 1, 1, 1, 1),
                        modificationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                        expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                        state = "pending",
                        topics = listOf("global"),
                        ownerId = "owner"
                    )
                )
            )
        ),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
