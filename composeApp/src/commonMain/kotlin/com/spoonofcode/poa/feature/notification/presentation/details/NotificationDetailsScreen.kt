package com.spoonofcode.poa.feature.notification.presentation.details

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
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Cards
import com.spoonofcode.poa.core.ui.compose.CountdownTimer
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.feature.notification.domain.model.Notification
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.notification
import com.spoonofcode.poa.resources.open_link
import org.jetbrains.compose.resources.stringResource

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

//                notification.expirationDateTime?.let { expiresAt ->
                    Spacers.VerticalBetweenFields()
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CountdownTimer(
                            targetTimeMillis = 1769727600000L
                        )
                    }
//                }

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
