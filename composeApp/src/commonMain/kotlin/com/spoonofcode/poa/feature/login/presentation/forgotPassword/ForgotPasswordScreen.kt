package com.spoonofcode.poa.feature.login.presentation.forgotPassword

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.email
import com.spoonofcode.poa.resources.reset_password
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ForgotPasswordScreen() :
    BaseScreen<ForgotPasswordViewModel, ForgotPasswordViewState, ForgotPasswordViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.reset_password

    @Composable
    override fun provideViewModel() = koinViewModel<ForgotPasswordViewModel>()

    @Composable
    override fun provideContent(
        viewState: ForgotPasswordViewState,
        onAction: (ForgotPasswordViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.email,
                onValueChange = { onAction(ForgotPasswordViewAction.ChangeEmail(email = it)) },
                label = stringResource(resource = Res.string.email),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.reset_password),
                onClick = { onAction(ForgotPasswordViewAction.ResetPassword) }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun ForgotPasswordScreenContentPreview() {
    ForgotPasswordScreen().PreviewContent(
        ForgotPasswordViewState()
    )
}
// endregion