package com.spoonofcode.poa.feature.notification.presentation.settings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.base.BaseScreen
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

            TextWithSwitchRow(
                text = stringResource(Res.string.from_all_partners),
                checked = viewState.notificationSettings.notificationFromAllPartnersEnable,
                onCheckedChange = {
                    onAction(
                        NotificationSettingsViewAction.SwitchNotificationFromAllPartners(
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