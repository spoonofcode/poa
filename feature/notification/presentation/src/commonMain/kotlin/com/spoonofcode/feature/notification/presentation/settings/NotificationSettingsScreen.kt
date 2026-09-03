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
import com.mohamedrejeb.calf.permissions.Notification
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.StandardAlertDialog
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.core.presentation.compose.RequestPermission
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Switchs
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.presentation.Res
import com.spoonofcode.feature.notification.presentation.notifications_all_push_description
import com.spoonofcode.feature.notification.presentation.notifications_all_push_title
import com.spoonofcode.feature.notification.presentation.notifications_from_partners_description
import com.spoonofcode.feature.notification.presentation.notifications_from_partners_title
import com.spoonofcode.feature.notification.presentation.notifications_my_product_series_description
import com.spoonofcode.feature.notification.presentation.notifications_my_product_series_title
import com.spoonofcode.feature.notification.presentation.notifications_personalized_description
import com.spoonofcode.feature.notification.presentation.notifications_personalized_title
import com.spoonofcode.feature.notification.presentation.notifications_settings
import com.spoonofcode.feature.notification.presentation.notifications_settings_text
import com.spoonofcode.feature.notification.presentation.open_app_settings
import com.spoonofcode.feature.notification.presentation.open_app_settings_info
import com.spoonofcode.feature.notification.presentation.rational_notification_dialog_confirm_button_text
import com.spoonofcode.feature.notification.presentation.rational_notification_dialog_dismiss_button_text
import com.spoonofcode.feature.notification.presentation.rational_notification_dialog_text
import com.spoonofcode.feature.notification.presentation.rational_notification_dialog_title
import com.spoonofcode.feature.notification.presentation.save_changes
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.notifications_settings),
    verticalScrollEnable = false,
)

@Composable
internal fun NotificationSettingsScreen(
    viewModel: NotificationSettingsViewModel,
    backNavigationEnable: Boolean = true,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig().copy(backNavigationEnable = backNavigationEnable),
        dialogs = { viewState, onAction ->
            Dialogs(viewState, onAction)
        }
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun ColumnScope.Content(
    viewState: NotificationSettingsViewState,
    onAction: (NotificationSettingsViewAction) -> Unit,
) {
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
            title = stringResource(Res.string.notifications_all_push_title),
            description = stringResource(Res.string.notifications_all_push_description),
            checked = viewState.isAllPushNotificationsSettingsEnable,
            onCheckedChange = {
                onAction(
                    NotificationSettingsViewAction.SwitchAllNotification(
                        it
                    )
                )
            },
        )

        Switchs.LabelSwitch(
            title = stringResource(Res.string.notifications_personalized_title),
            description = stringResource(Res.string.notifications_personalized_description),
            checked = viewState.notificationSettings.isNotificationsPersonalizedEnabled,
            onCheckedChange = {
                onAction(
                    NotificationSettingsViewAction.SwitchNotificationsPersonalized(
                        it
                    )
                )
            },
        )

        Switchs.LabelSwitch(
            title = stringResource(Res.string.notifications_from_partners_title),
            description = stringResource(Res.string.notifications_from_partners_description),
            checked = viewState.notificationSettings.isNotificationsFromPartnersEnabled,
            onCheckedChange = {
                onAction(
                    NotificationSettingsViewAction.SwitchNotificationsFromPartners(
                        it
                    )
                )
            },
        )

        Switchs.LabelSwitch(
            title = stringResource(Res.string.notifications_my_product_series_title),
            description = stringResource(Res.string.notifications_my_product_series_description),
            checked = viewState.notificationSettings.isNotificationsMyProductSeriesEnabled,
            onCheckedChange = {
                onAction(
                    NotificationSettingsViewAction.SwitchNotificationsMyProductSeries(
                        it
                    )
                )
            },
        )


        Spacers.Weight1(this)

        PrimaryButton(
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
            PrimaryButton(
                text = stringResource(resource = Res.string.open_app_settings),
                onClick = {
                    notificationPermissionState.openAppSettings()
                }
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun Dialogs(
    viewState: NotificationSettingsViewState,
    onAction: (NotificationSettingsViewAction) -> Unit
) {
    val notificationPermissionState = rememberPermissionState(
        Permission.Notification
    )

    if (viewState.isRationaleNotificationDialogVisible) {
        StandardAlertDialog(
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

// region previews
@Preview
@Composable
private fun NotificationSettingsScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            NotificationSettingsViewState(
                notificationSettings = NotificationSettings(
                    isNotificationsPersonalizedEnabled = true,
                    isNotificationsFromPartnersEnabled = true,
                    isNotificationsMyProductSeriesEnabled = true,
                )
            ),
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
