package com.spoonofcode.feature.notification.presentation.channel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Sms
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons.IconTextButton
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.notification.presentation.Res
import com.spoonofcode.feature.notification.presentation.notification_channels
import com.spoonofcode.feature.notification.presentation.notification_email
import com.spoonofcode.feature.notification.presentation.notification_push
import com.spoonofcode.feature.notification.presentation.notification_tel_and_sms
import org.jetbrains.compose.resources.stringResource

internal class NotificationChannelScreen(
    val viewModel: NotificationChannelViewModel,
) :
    BaseScreen<NotificationChannelViewModel, NotificationChannelViewState, NotificationChannelViewAction, Nothing>() {

    @Composable
    override fun provideTopAppBarTitle() = stringResource(Res.string.notification_channels)

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationChannelViewModel>()

    @Composable
    override fun provideContent(
        viewState: NotificationChannelViewState,
        onAction: (NotificationChannelViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.Notifications,
                    text = stringResource(resource = Res.string.notification_push),
                    onClick = { onAction(NotificationChannelViewAction.SelectNotificationPush) },
                    modifier = Modifier.weight(1f),
                )

                IconTextButton(
                    icon = Icons.Default.Email,
                    text = stringResource(resource = Res.string.notification_email),
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    enabled = viewState.buttonEmailEnable,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.Sms,
                    text = stringResource(resource = Res.string.notification_tel_and_sms),
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    enabled = viewState.buttonTelAndSmsEnable,
                )

                Box(
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationChannelScreenContentPreview() {
    NotificationChannelScreen()
        .PreviewContent(
            NotificationChannelViewState()
        )
}
// endregion