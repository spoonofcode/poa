package com.spoonofcode.feature.notification.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.data.ext.toMillis
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.CountdownTimer
import com.spoonofcode.core.presentation.compose.ElevatedCard
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.core.presentation.compose.SpacerVerticalBetweenFields
import com.spoonofcode.core.presentation.compose.TextBL
import com.spoonofcode.core.presentation.compose.TextTLB
import com.spoonofcode.feature.notification.domain.model.Notification
import com.spoonofcode.feature.notification.presentation.Res
import com.spoonofcode.feature.notification.presentation.notification
import com.spoonofcode.feature.notification.presentation.open_link
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.notification),
)

@Composable
internal fun NotificationDetailsScreen(
    viewModel: NotificationDetailsViewModel,
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
    viewState: NotificationDetailsViewState,
    onAction: (NotificationDetailsViewAction) -> Unit,
) {
    val notification = requireNotNull(viewState.notification)
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NotificationCard(
            notification = notification,
            onOpenLink = { uriHandler.openUri(it) }
        )
    }
}

@Composable
private fun NotificationCard(
    notification: Notification,
    onOpenLink: (String) -> Unit = {}
) {
    ElevatedCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            TextTLB(
                text = notification.title,
                color = MaterialTheme.colorScheme.onSurface
            )

            SpacerVerticalBetweenFields()

            TextBL(
                text = notification.text,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            notification.expirationDateTime.let { expiresAt ->
                SpacerVerticalBetweenFields()
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CountdownTimer(
                        targetTimeMillis = expiresAt.toMillis()
                    )
                }
            }

            notification.link
                ?.takeIf { it.isNotBlank() }
                ?.let { link ->
                    SpacerVerticalBetweenFields()
                    PrimaryButton(
                        text = stringResource(Res.string.open_link),
                        onClick = { onOpenLink(link) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationDetailsScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            NotificationDetailsViewState(
                notification = Notification(
                    id = "1",
                    title = "Notification title 1",
                    text = "Notification text 1",
                    link = "Notification link 1",
                    deliveryDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    createdDataTime = LocalDateTime(2023, 1, 1, 1, 1),
                    modificationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    state = "pending",
                    topics = listOf("global"),
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
