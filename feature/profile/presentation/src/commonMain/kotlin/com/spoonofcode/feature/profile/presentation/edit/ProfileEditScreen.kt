package com.spoonofcode.feature.profile.presentation.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.feature.profile.presentation.Res
import com.spoonofcode.feature.profile.presentation.edit_profile
import com.spoonofcode.feature.profile.presentation.name
import com.spoonofcode.feature.profile.presentation.poa
import com.spoonofcode.feature.profile.presentation.save_changes
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.edit_profile),
)

@Composable
internal fun ProfileEditScreen(
    viewModel: ProfileEditViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: ProfileEditViewState,
    onAction: (ProfileEditViewAction) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Images.CircleImage(imageRes = Res.drawable.poa)
    }

    TextFields.Outlined(
        value = viewState.profile!!.email,
        onValueChange = { onAction(ProfileEditViewAction.ChangeCustomLink(it)) },
        label = stringResource(resource = Res.string.name),
    )

    Spacers.Weight1(this)

    Buttons.PrimaryButton(
        text = stringResource(Res.string.save_changes),
        onClick = { onAction(ProfileEditViewAction.SaveChanges) }
    )
}

// region previews
@Preview
@Composable
private fun ProfileEditScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(ProfileEditViewState()),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
