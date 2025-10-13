package com.spoonofcode.poa.feature.notification.settings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.TextWithSwitchRow
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.from_all_partners
import com.spoonofcode.poa.resources.from_my_partners
import com.spoonofcode.poa.resources.notifications_settings
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationSettingsScreen :
    BaseScreen<NotificationSettingsViewModel, NotificationSettingsViewState>() {

    override fun provideTopAppBarTitle() = Res.string.notifications_settings

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationSettingsViewModel>()

    @Composable
    override fun provideContent(
        viewModel: NotificationSettingsViewModel?,
        viewState: NotificationSettingsViewState,
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
    )

    @Composable
    private fun ContentView(
        viewState: NotificationSettingsViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextWithSwitchRow(
                text = stringResource(Res.string.from_all_partners),
                checked = true,
                onCheckedChange = { }
            )

            TextWithSwitchRow(
                text = stringResource(Res.string.from_my_partners),
                checked = true,
                onCheckedChange = { }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationSettingsScreenContentPreview() {
    NotificationSettingsScreen().PreviewContent(
        NotificationSettingsViewState()
    )
}
// endregion