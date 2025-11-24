package com.spoonofcode.poa.feature.profile.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.CircleImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.edit_profile
import com.spoonofcode.poa.resources.name
import com.spoonofcode.poa.resources.poa
import com.spoonofcode.poa.resources.save_changes
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ProfileEditScreen() : BaseScreen<ProfileEditViewModel, ProfileEditViewState>() {

    override fun provideTopAppBarTitle() = Res.string.edit_profile

    override fun provideInitAction(viewModel: ProfileEditViewModel?): () -> Unit =
        { viewModel?.onAction(ProfileEditViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<ProfileEditViewModel>()

    @Composable
    override fun provideContent(
        viewModel: ProfileEditViewModel?,
        viewState: ProfileEditViewState
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        onAction = viewModel!!::onAction,
    )

    @Composable
    private fun ContentView(
        viewState: ProfileEditViewState,
        onAction: (ProfileEditViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                CircleImage(imageRes = Res.drawable.poa)
            }

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.profile!!.name ?: "",
                onValueChange = { onAction(ProfileEditViewAction.ChangeCustomLink(it)) },
                label = stringResource(resource = Res.string.name),
            )

            Spacers.VerticalBetweenFields()

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(Res.string.save_changes),
                onClick = { onAction(ProfileEditViewAction.SaveChanges) }
            )
        }
    }
}

// region previews
@Preview
@Composable
fun ProfileEditScreenContentPreview() {
    ProfileEditScreen().PreviewContent(
        viewState = ProfileEditViewState(),
    )
}
// endregion