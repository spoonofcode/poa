package com.spoonofcode.feature.notification.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Switchs
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.notification.presentation.Res
import com.spoonofcode.feature.notification.presentation.from_all_partners
import com.spoonofcode.feature.notification.presentation.from_my_partners
import com.spoonofcode.feature.notification.presentation.notifications_settings
import com.spoonofcode.feature.notification.presentation.notifications_settings_text
import com.spoonofcode.feature.notification.presentation.open_settings
import com.spoonofcode.feature.notification.presentation.open_settings_info
import com.spoonofcode.feature.notification.presentation.save_changes
import org.jetbrains.compose.resources.stringResource

internal class NotificationSettingsScreen(
    override val verticalScrollEnable: Boolean = false,
) :
    BaseScreen<NotificationSettingsViewModel, NotificationSettingsViewState, NotificationSettingsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notifications_settings

    override fun provideInitAction(onAction: (NotificationSettingsViewAction) -> Unit): () -> Unit =
        { onAction(NotificationSettingsViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationSettingsViewModel>()

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    override fun provideContent(
        viewState: NotificationSettingsViewState,
        onAction: (NotificationSettingsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            val notificationPermissionState = rememberPermissionState(
                Permission.Notification
            )

            if (notificationPermissionState.status.isGranted) {
                Texts.TM(stringResource(Res.string.notifications_settings_text))

                Switchs.LabelSwitch(
                    label = stringResource(Res.string.from_all_partners),
                    checked = viewState.notificationSettings.notificationFromAllPartnersEnable,
                    onCheckedChange = {
                        onAction(
                            NotificationSettingsViewAction.SwitchNotificationFromAllPartners(
                                it
                            )
                        )
                    },
                )

                Switchs.LabelSwitch(
                    label = stringResource(Res.string.from_my_partners),
                    checked = viewState.notificationSettings.notificationFromMyPartnersEnable,
                    onCheckedChange = {
                        onAction(
                            NotificationSettingsViewAction.SwitchNotificationFromMyPartners(
                                it
                            )
                        )
                    },
                )

                Spacers.Weight1(this)

                Buttons.PrimaryButton(
                    text = stringResource(Res.string.save_changes),
                    onClick = { onAction(NotificationSettingsViewAction.SaveChanges) }
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Texts.BL(
                        text = stringResource(resource = Res.string.open_settings_info),
                        textAlign = TextAlign.Center,
                    )
                    Spacers.VerticalBetweenFields()
                    Buttons.PrimaryButton(
                        text = stringResource(resource = Res.string.open_settings),
                        onClick = { notificationPermissionState.launchPermissionRequest() }
                    )
                }
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationSettingsScreenContentPreview() {
    NotificationSettingsScreen()
        .PreviewContent(
            NotificationSettingsViewState()
        )
}
// endregion