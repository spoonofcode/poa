package com.spoonofcode.poa.feature.notification.presentation.settings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextWithSwitchRow
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.from_all_partners
import com.spoonofcode.poa.resources.from_my_partners
import com.spoonofcode.poa.resources.notifications_settings
import com.spoonofcode.poa.resources.notifications_settings_text
import com.spoonofcode.poa.resources.save_changes
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationSettingsScreen :
    BaseScreen<com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewModel, com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState, com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notifications_settings

    override fun provideInitAction(onAction: (com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction) -> Unit): () -> Unit =
        { onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewModel>()

    @Composable
    override fun provideContent(
        viewState: com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState,
        onAction: (com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.TM(stringResource(Res.string.notifications_settings_text))

            TextWithSwitchRow(
                text = stringResource(Res.string.from_all_partners),
                checked = viewState.notificationSettings.notificationFromAllPartnersEnable,
                onCheckedChange = {
                    onAction(
                        _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction.SwitchNotificationFromAllPartners(
                            it
                        )
                    )
                },
            )

            TextWithSwitchRow(
                text = stringResource(Res.string.from_my_partners),
                checked = viewState.notificationSettings.notificationFromMyPartnersEnable,
                onCheckedChange = {
                    onAction(
                        _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction.SwitchNotificationFromMyPartners(
                            it
                        )
                    )
                },
            )

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(Res.string.save_changes),
                onClick = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewAction.SaveChanges) }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationSettingsScreenContentPreview() {
    _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsScreen()
        .PreviewContent(
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewState()
    )
}
// endregion