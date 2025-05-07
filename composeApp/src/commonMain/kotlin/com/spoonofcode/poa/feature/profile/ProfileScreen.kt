package com.spoonofcode.poa.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
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
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.dojo_pro
import com.spoonofcode.poa.resources.profile
import com.spoonofcode.poa.resources.settings
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class ProfileScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.profile,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ProfileViewModel, ProfileViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<ProfileViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ProfileViewModel,
        viewState: ProfileViewState
    ): @Composable ColumnScope.() -> Unit {
        super.reloadScreen = { viewModel.initView() }

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
                RoundedImage(imageRes = Res.drawable.dojo_pro)
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
    // TODO This functions should be private and with @Preview annotation but now
    //  Android Studio will be support previews in commonMain
    @Composable
    fun InitializedProfileScreenPreview() {
        ContentView(
            content = ContentView(
                viewState = ProfileViewState(
                    profile = Profile(
                        name = "Bartosz luczak (Lycha)",
                        numberOfEventsCreatedByUser = 1,
                        numberOfEventsUserParticipatedIn = 2
                    )
                ),
            )
        )
    }
    // endregion
}