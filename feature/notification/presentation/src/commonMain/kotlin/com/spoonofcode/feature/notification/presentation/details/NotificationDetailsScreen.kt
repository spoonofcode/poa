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
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.data.ext.toMillis
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.CountdownTimer
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.feature.notification.domain.model.Notification
import com.spoonofcode.feature.notification.presentation.Res
import com.spoonofcode.feature.notification.presentation.notification
import com.spoonofcode.feature.notification.presentation.open_link
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NotificationDetailsScreen(
    viewModel: NotificationDetailsViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        topBarTitle = stringResource(Res.string.notification),
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
    Cards.ElevatedCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Texts.TLB(
                text = notification.title,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacers.VerticalBetweenFields()

            Texts.BL(
                text = notification.text,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            notification.expirationDateTime?.let { expiresAt ->
                Spacers.VerticalBetweenFields()
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
                    Spacers.VerticalBetweenFields()
                    Buttons.PrimaryButton(
                        text = stringResource(Res.string.open_link),
                        onClick = { onOpenLink(link) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
        }
    }
}

// region previews
//@Preview
//@Composable
//private fun NotificationDetailsScreenContentPreview() {
//    NotificationDetailsScreen(
//        notificationId = "1"
//    ).PreviewContent(
//        NotificationDetailsViewState()
//    )
//}
// endregion
