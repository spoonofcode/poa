package com.spoonofcode.feature.tac.presentation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Checkboxes
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun TACScreen(
    viewModel: TACViewModel,
    backNavigationEnable: Boolean = false,
) {
    StandardScreen(
        viewModel = viewModel,
        backNavigationEnable = backNavigationEnable,
        topBarTitle = stringResource(Res.string.terms_and_conditions),
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
    onAction: (TACViewAction) -> Unit
) {
    Texts.HSB(stringResource(Res.string.terms_and_conditions_description))

    Texts.LS(
        text = stringResource(Res.string.star_mandatory),
        color = MaterialTheme.colorScheme.error,
    )

    Checkboxes.CheckboxTextLinkRow(
        checked = viewState.isPrivacyPolicyAccepted,
        onCheckedChange = { onAction(TACViewAction.ChangePrivacyPolicy(it)) },
        text = stringResource(Res.string.privacy_policy),
        linkText = stringResource(Res.string.read),
        linkUrl = viewState.privacyPolicyLink,
    )

    Checkboxes.CheckboxTextLinkRow(
        checked = viewState.isTermsAndConditionsAccepted,
        onCheckedChange = { onAction(TACViewAction.ChangeTermsAndConditions(it)) },
        text = stringResource(Res.string.terms_and_conditions),
        linkText = stringResource(Res.string.read),
        linkUrl = viewState.marketingLink,
    )

    Spacers.Weight1(this)

    Buttons.PrimaryButton(
        text = stringResource(resource = Res.string.save_changes),
        enabled = viewState.isSaveChangesButtonEnable,
        onClick = { onAction(TACViewAction.SaveChanges) }
    )
}

// region previews
//@Preview
//@Composable
//fun TACScreenContentPreview() {
//    TACScreen().PreviewContent(
//        viewState = TACViewState(),
//    )
//}
// endregion