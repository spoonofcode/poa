package com.spoonofcode.poa.feature.tac.presentation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Checkboxes
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import poa.core.presentation.generated.resources.Res
import poa.core.presentation.generated.resources.privacy_policy
import poa.core.presentation.generated.resources.read
import poa.core.presentation.generated.resources.save_changes
import poa.core.presentation.generated.resources.star_mandatory
import poa.core.presentation.generated.resources.terms_and_conditions
import poa.core.presentation.generated.resources.terms_and_conditions_description
import org.jetbrains.compose.resources.stringResource

internal class TACScreen(
    override val verticalScrollEnable: Boolean = true,
) : BaseScreen<TACViewModel, TACViewState, TACViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.terms_and_conditions

    override fun provideInitAction(onAction: (TACViewAction) -> Unit): () -> Unit =
        { onAction(TACViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<TACViewModel>()

    @Composable
    override fun provideContent(
        viewState: TACViewState,
        onAction: (TACViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.HSB(stringResource(Res.string.terms_and_conditions_description))

            Texts.LS(
                text = stringResource(Res.string.star_mandatory),
                color = MaterialTheme.colorScheme.error,
            )

            Checkboxes.CheckboxTextLinkRow(
                checked = viewState.isPrivacyPolicyAccepted,
                onCheckedChange = { onAction(TACViewAction.ChangePrivacyPolicyAccepted(it)) },
                text = stringResource(Res.string.privacy_policy),
                linkText = stringResource(Res.string.read),
                linkUrl = "https://example.com/privacy-policy"
            )

            Checkboxes.CheckboxTextLinkRow(
                checked = viewState.isTermsAndConditionsAccepted,
                onCheckedChange = { onAction(TACViewAction.ChangeTermsAndConditionsAccepted(it)) },
                text = stringResource(Res.string.terms_and_conditions),
                linkText = stringResource(Res.string.read),
                linkUrl = "https://example.com/terms-and-conditions"
            )

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.save_changes),
                enabled = viewState.isPrivacyPolicyAccepted && viewState.isTermsAndConditionsAccepted,
                onClick = { onAction(TACViewAction.SaveChanges) }
            )
        }
    }
}

// region previews
@Preview
@Composable
fun TACScreenContentPreview() {
    TACScreen().PreviewContent(
        viewState = TACViewState(),
    )
}
// endregion