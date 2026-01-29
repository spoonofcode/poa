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
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.from_all_partners
import com.spoonofcode.core.presentation.from_my_partners
import com.spoonofcode.core.presentation.notifications_settings
import com.spoonofcode.core.presentation.notifications_settings_text
import com.spoonofcode.core.presentation.save_changes
import org.jetbrains.compose.resources.stringResource

internal class NotificationSettingsScreen :
    BaseScreen<com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewModel, com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewState, com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notifications_settings

    override fun provideInitAction(onAction: (com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction) -> Unit): () -> Unit =
        { onAction(_root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewModel>()

    @Composable
    override fun provideContent(
        viewState: com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewState,
        onAction: (com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.TM(stringResource(Res.string.notifications_settings_text))

            Switchs.LabelSwitch(
                label = stringResource(Res.string.from_all_partners),
                checked = viewState.notificationSettings.notificationFromAllPartnersEnable,
                onCheckedChange = {
                    onAction(
                        _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction.SwitchNotificationFromAllPartners(
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
                        _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction.SwitchNotificationFromMyPartners(
                            it
                        )
                    )
                },
            )

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(Res.string.save_changes),
                onClick = { onAction(_root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction.SaveChanges) }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationSettingsScreenContentPreview() {
    _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsScreen()
        .PreviewContent(
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewState()
        )
}
// endregion