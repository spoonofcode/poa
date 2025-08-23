package com.spoonofcode.poa.feature.appsettings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.delete_account
import com.spoonofcode.poa.resources.message_fcm
import com.spoonofcode.poa.resources.settings
import com.spoonofcode.poa.resources.sign_out
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

internal class AppSettingsScreen() : BaseScreen<AppSettingsViewModel, AppSettingsViewState>() {

    override fun provideTopAppBarTitle() = Res.string.settings

    override fun provideReloadAction(viewModel: AppSettingsViewModel): () -> Unit =
        { viewModel.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<AppSettingsViewModel>()

    @Composable
    override fun provideContent(
        viewModel: AppSettingsViewModel?,
        viewState: AppSettingsViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            navigateToMessageFCM = { viewModel?.navigateToMessageFCM() },
            signOut = { viewModel?.signOut() },
            deleteAccount = { viewModel?.deleteAccount() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: AppSettingsViewState,
        navigateToMessageFCM: () -> Unit,
        signOut: () -> Unit,
        deleteAccount: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.message_fcm),
                onClick = navigateToMessageFCM
            )

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_out),
                onClick = signOut
            )

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.delete_account),
                onClick = deleteAccount
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun AppSettingsScreenContentPreview() {
    AppSettingsScreen().PreviewContent(
        AppSettingsViewState()
    )
}
// endregion