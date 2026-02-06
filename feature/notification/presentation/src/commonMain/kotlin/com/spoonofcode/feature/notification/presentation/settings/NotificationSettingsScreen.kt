package com.spoonofcode.feature.notification.presentation.settings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
import com.spoonofcode.feature.notification.presentation.save_changes
import org.jetbrains.compose.resources.stringResource

internal class NotificationSettingsScreen :
    BaseScreen<NotificationSettingsViewModel, NotificationSettingsViewState, NotificationSettingsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notifications_settings

    override fun provideInitAction(onAction: (NotificationSettingsViewAction) -> Unit): () -> Unit =
        { onAction(NotificationSettingsViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationSettingsViewModel>()

    @Composable
    override fun provideContent(
        viewState: NotificationSettingsViewState,
        onAction: (NotificationSettingsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
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