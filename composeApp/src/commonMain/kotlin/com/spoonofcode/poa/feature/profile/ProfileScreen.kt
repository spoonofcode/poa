package com.spoonofcode.poa.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.RoundedImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.core.ui.theme.AppTheme
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.poa
import com.spoonofcode.poa.resources.profile
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ProfileScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ProfileViewModel, ProfileViewState>() {

    override fun provideTopAppBarTitle() = Res.string.profile

    override fun provideTopBarActions(
        viewModel: ProfileViewModel,
    ) = listOf(
        TopBarAction(
            icon = Icons.Default.Settings,
            description = "settings",
            onClick = { viewModel.navigateToSettings() }
        ),
    )

    override fun provideReloadAction(viewModel: ProfileViewModel): () -> Unit =
        { viewModel.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<ProfileViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ProfileViewModel,
        viewState: ProfileViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: ProfileViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                RoundedImage(imageRes = Res.drawable.poa)
                Spacers.VerticalBetweenFields()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Texts.HSB(viewState.profile!!.name)
                Spacers.VerticalBetweenFields()
            }

        }
    }

    // region previews
    @Preview
    @Composable
    private fun InitializedProfileScreenPreview() {
        AppTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ContentView(
                    viewState = ProfileViewState(
                        profile = Profile(
                            name = "Bartosz luczak (Lycha)",
                            numberOfProductsOwnedByUser = 2
                        )
                    ),
                )
            }
        }
    }
    // endregion
}