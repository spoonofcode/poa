package com.spoonofcode.feature.notification.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import com.spoonofcode.core.presentation.compose.Dialogs
import com.spoonofcode.core.presentation.compose.RequestPermission
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Switchs
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.notification.presentation.Res
import com.spoonofcode.feature.notification.presentation.notifications_for_selected_product_series
import com.spoonofcode.feature.notification.presentation.notifications_settings
import com.spoonofcode.feature.notification.presentation.notifications_settings_text
import com.spoonofcode.feature.notification.presentation.open_app_settings
import com.spoonofcode.feature.notification.presentation.open_app_settings_info
import com.spoonofcode.feature.notification.presentation.personalized_notifications_from_partners
import com.spoonofcode.feature.notification.presentation.rational_notification_dialog_confirm_button_text
import com.spoonofcode.feature.notification.presentation.rational_notification_dialog_dismiss_button_text
import com.spoonofcode.feature.notification.presentation.rational_notification_dialog_text
import com.spoonofcode.feature.notification.presentation.rational_notification_dialog_title
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
    override fun provideDialogs(
        viewState: NotificationSettingsViewState,
        onAction: (NotificationSettingsViewAction) -> Unit
    ) {
        val notificationPermissionState = rememberPermissionState(
            Permission.Notification
        )

        if (viewState.isRationaleNotificationDialogVisible) {
            Dialogs.AlertDialog(
                title = stringResource(Res.string.rational_notification_dialog_title),
                text = stringResource(Res.string.rational_notification_dialog_text),
                confirmButtonText = stringResource(Res.string.rational_notification_dialog_confirm_button_text),
                dismissButtonText = stringResource(Res.string.rational_notification_dialog_dismiss_button_text),
                confirmAction = {
                    onAction(NotificationSettingsViewAction.CloseRationaleNotificationDialog)
                    notificationPermissionState.launchPermissionRequest()
                },
                dismissAction = { onAction(NotificationSettingsViewAction.CloseRationaleNotificationDialog) }
            )
        }
    }

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

            RequestPermission(
                notificationPermissionState = notificationPermissionState,
                onGrantedAction = { onAction(NotificationSettingsViewAction.AcceptNotificationSettings) },
                onShowRationalAction = { onAction(NotificationSettingsViewAction.ShowRationaleNotificationDialog) }
            )

            if (notificationPermissionState.status.isGranted) {
                Texts.TM(stringResource(Res.string.notifications_settings_text))

                Switchs.LabelSwitch(
                    label = stringResource(Res.string.personalized_notifications_from_partners),
                    checked = viewState.notificationSettings.isPersonalizedPartnerNotificationsEnabled,
                    onCheckedChange = {
                        onAction(
                            NotificationSettingsViewAction.SwitchPersonalizedNotificationsFromPartners(
                                it
                            )
                        )
                    },
                )

                Switchs.LabelSwitch(
                    label = stringResource(Res.string.notifications_for_selected_product_series),
                    checked = viewState.notificationSettings.isProductSeriesNotificationsEnabled,
                    onCheckedChange = {
                        onAction(
                            NotificationSettingsViewAction.SwitchNotificationsForSelectedProductSeries(
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
                        text = stringResource(resource = Res.string.open_app_settings_info),
                        textAlign = TextAlign.Center,
                    )
                    Spacers.VerticalBetweenFields()
                    Buttons.PrimaryButton(
                        text = stringResource(resource = Res.string.open_app_settings),
                        onClick = {
                            notificationPermissionState.openAppSettings()
                        }
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