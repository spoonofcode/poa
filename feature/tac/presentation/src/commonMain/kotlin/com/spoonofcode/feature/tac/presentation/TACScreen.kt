package com.spoonofcode.feature.tac.presentation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.CheckboxTextLinkRow
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.core.presentation.compose.SpacerWeight1
import com.spoonofcode.core.presentation.compose.TextHSB
import com.spoonofcode.core.presentation.compose.TextLS
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig =
    StandardScreenConfig().copy(
        topBarTitle = stringResource(Res.string.terms_and_conditions),
    )

@Composable
internal fun TACScreen(
    viewModel: TACViewModel,
    backNavigationEnable: Boolean = false,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig().copy(backNavigationEnable = backNavigationEnable),
        isViewEnable = { it.isViewEnable },
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: TACViewState,
    onAction: (TACViewAction) -> Unit,
) {
    TextHSB(stringResource(Res.string.terms_and_conditions_description))

    TextLS(
        text = stringResource(Res.string.star_mandatory),
        color = MaterialTheme.colorScheme.error,
    )

    CheckboxTextLinkRow(
        checked = viewState.isPrivacyPolicyAccepted,
        onCheckedChange = { onAction(TACViewAction.ChangePrivacyPolicy(it)) },
        text = stringResource(Res.string.privacy_policy),
        linkText = stringResource(Res.string.read),
        linkUrl = viewState.privacyPolicyLink,
    )

    CheckboxTextLinkRow(
        checked = viewState.isTermsAndConditionsAccepted,
        onCheckedChange = { onAction(TACViewAction.ChangeTermsAndConditions(it)) },
        text = stringResource(Res.string.terms_and_conditions),
        linkText = stringResource(Res.string.read),
        linkUrl = viewState.marketingLink,
    )

    SpacerWeight1(this)

    PrimaryButton(
        text = stringResource(resource = Res.string.save_changes),
        enabled = viewState.isSaveChangesButtonEnable,
        isLoading = viewState.isSaveChangesButtonLoading,
        onClick = { onAction(TACViewAction.SaveChanges) },
    )
}

// region previews
@Preview
@Composable
private fun TACScreenContentPreview() {
    StandardScreenPreview(
        viewState =
            ViewState.Content(
                TACViewState(
                    isPrivacyPolicyAccepted = false,
                    isTermsAndConditionsAccepted = false,
                    privacyPolicyLink = "",
                    marketingLink = "",
                ),
            ),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
