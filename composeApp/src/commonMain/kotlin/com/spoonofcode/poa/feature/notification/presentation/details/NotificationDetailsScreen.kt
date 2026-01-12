package com.spoonofcode.poa.feature.notification.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.feature.notification.domain.model.Notification
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Countdown
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.notification
import com.spoonofcode.poa.resources.open_link
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationDetailsScreen(
    private val notificationId: String,
) : BaseScreen<NotificationDetailsViewModel, NotificationDetailsViewState, NotificationDetailsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notification

    override fun provideInitAction(onAction: (NotificationDetailsViewAction) -> Unit): () -> Unit =
        { onAction(NotificationDetailsViewAction.InitView(notificationId = notificationId)) }

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationDetailsViewModel>()

    @Composable
    override fun provideContent(
        viewState: NotificationDetailsViewState,
        onAction: (NotificationDetailsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            val notification = requireNotNull(viewState.notification)

            val uriHandler = LocalUriHandler.current

            NotificationCard(
                notification = notification,
                onOpenLink = { uriHandler.openUri(it) }
            )
        }
    }

    @Composable
    fun NotificationCard(
        notification: Notification,
        modifier: Modifier = Modifier,
        onOpenLink: (String) -> Unit = {}
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Texts.TM(text = notification.title)

                Spacers.VerticalBetweenFields()

                Texts.BM(text = notification.text)

                notification.expirationDateTime?.let { expiresAt ->
                    Spacers.VerticalBetweenFields()
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        tonalElevation = 2.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Countdown(expiresAt)
                        }
                    }
                }

                notification.link
                    ?.takeIf { it.isNotBlank() }
                    ?.let { link ->
                        Spacers.VerticalBetweenFields()
                        Buttons.PrimaryButton(
                            text = stringResource(Res.string.open_link),
                            onClick = { onOpenLink(link) },
                        )
                    }
            }
        }
    }

}

// region previews
@Preview
@Composable
private fun NotificationDetailsScreenContentPreview() {
    NotificationDetailsScreen(
        notificationId = "1"
    ).PreviewContent(
        NotificationDetailsViewState()
    )
}
// endregion