package com.spoonofcode.poa.feature.tac

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.CheckboxTextLinkRow
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.privacy_policy
import com.spoonofcode.poa.resources.read
import com.spoonofcode.poa.resources.save_changes
import com.spoonofcode.poa.resources.star_mandatory
import com.spoonofcode.poa.resources.terms_and_conditions
import com.spoonofcode.poa.resources.terms_and_conditions_description
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class TACScreen(
    override val verticalScrollEnable: Boolean = false,
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
            Texts.HS(stringResource(Res.string.terms_and_conditions_description))

            Text(
                text = stringResource(Res.string.star_mandatory),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )

            CheckboxTextLinkRow(
                checked = true,
                onCheckedChange = { /* update state */ },
                text = stringResource(Res.string.privacy_policy),
                linkText = stringResource(Res.string.read),
                linkUrl = "https://example.com/terms"
            )
            CheckboxTextLinkRow(
                checked = true,
                onCheckedChange = { /* update state */ },
                text = stringResource(Res.string.terms_and_conditions),
                linkText = stringResource(Res.string.read),
                linkUrl = "https://example.com/terms"
            )

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.save_changes),
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