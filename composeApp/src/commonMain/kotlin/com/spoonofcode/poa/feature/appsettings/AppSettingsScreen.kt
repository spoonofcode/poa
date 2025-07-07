package com.spoonofcode.poa.feature.appsettings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.message_fcm
import com.spoonofcode.poa.resources.settings
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class AppSettingsScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.settings,
) : BaseScreen<AppSettingsViewModel, AppSettingsViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<AppSettingsViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: AppSettingsViewModel,
        viewState: AppSettingsViewState
    ): @Composable ColumnScope.() -> Unit {
        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            navigateToMessageFCM = { viewModel.navigateToMessageFCM() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: AppSettingsViewState,
        navigateToMessageFCM: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BLB(stringResource(Res.string.settings))

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.message_fcm),
                onClick = navigateToMessageFCM
            )
        }
    }
}