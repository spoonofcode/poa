package com.spoonofcode.feature.tac.presentation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Checkboxes
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import org.koin.compose.viewmodel.koinViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

internal class TACScreen(
    override val backNavigationEnable: Boolean = true,
    override val verticalScrollEnable: Boolean = true,
    val viewModel: TACViewModel,
    ) : BaseScreen<TACViewModel, TACViewState, TACViewAction, Nothing>() {

    @Composable
    override fun provideTopAppBarTitle() = stringResource(Res.string.terms_and_conditions)

    @Composable
    override fun provideViewModel() = koinViewModel<TACViewModel>()

    override fun provideInitAction(onAction: (TACViewAction) -> Unit): () -> Unit =
        { onAction(TACViewAction.InitView) }

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
    }
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