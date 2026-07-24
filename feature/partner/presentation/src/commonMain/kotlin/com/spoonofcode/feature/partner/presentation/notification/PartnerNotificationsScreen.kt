package com.spoonofcode.feature.partner.presentation.notification

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
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import org.koin.compose.viewmodel.koinViewModel
import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.my_notifications
import com.spoonofcode.feature.partner.presentation.tab_already_sent
import com.spoonofcode.feature.partner.presentation.tab_pending
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock

internal class PartnerNotificationsScreen(
    override val verticalScrollEnable: Boolean = false,
    val viewModel: PartnerNotificationsViewModel,
) : BaseScreen<PartnerNotificationsViewModel, PartnerNotificationsViewState, PartnerNotificationsViewAction, Nothing>() {

    @Composable
    override fun provideTopAppBarTitle() = stringResource(Res.string.my_notifications)

    @Composable
    override fun provideViewModel() = viewModel

    override fun provideContentPadding() = PaddingValues(
        start = Paddings.screenPadding,
        end = Paddings.screenPadding,
    )

    @Composable
    override fun provideContent(
        viewState: PartnerNotificationsViewState,
        onAction: (PartnerNotificationsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            NotificationTabs(
                viewState.partnerNotifications,
                selectNotification = { onAction(PartnerNotificationsViewAction.SelectNotification(it)) },
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NotificationTabs(
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

    data class TabItem(val title: String)

    @Composable
    fun NotificationTab(
        notifications: List<PartnerNotification>,
        selectNotification: (String) -> Unit
    ) {
        Spacers.VerticalBetweenFields()
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
    fun NotificationItem(
        item: PartnerNotification,
        onClick: () -> Unit,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Cards.ElevatedCard(
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

                        Texts.TM(
                            text = item.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Texts.BM(
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
}

// region previews
//@Preview
//@Composable
//private fun PartnerNotificationsScreenContentPreview() {
//    PartnerNotificationsScreen()
//        .PreviewContent(
//            PartnerNotificationsViewState()
//        )
//}
// endregion