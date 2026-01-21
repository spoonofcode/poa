package com.spoonofcode.poa.feature.notification.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Countdown
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.feature.notification.domain.model.Notification
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.notification
import com.spoonofcode.poa.resources.open_link
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview

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
    }

    @Composable
    fun NotificationCard(
        notification: Notification,
        modifier: Modifier = Modifier,
        onOpenLink: (String) -> Unit = {}
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Text(
                        text = notification.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacers.VerticalBetweenFields()

                Text(
                    text = notification.text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 24.sp
                )

                notification.expirationDateTime?.let { expiresAt ->
                    Spacers.VerticalBetweenFields()
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
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
                            modifier = Modifier.fillMaxWidth()
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
