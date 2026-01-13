package com.spoonofcode.poa.feature.login.presentation.logincode

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.code
import com.spoonofcode.poa.resources.sign_in_with_code
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class LoginCodeScreen(
    val email: String,
) :
    BaseScreen<LoginCodeViewModel, LoginCodeViewState, LoginCodeViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.sign_in_with_code

    @Composable
    override fun provideViewModel() = koinViewModel<LoginCodeViewModel>()

    override fun provideInitAction(onAction: (LoginCodeViewAction) -> Unit): () -> Unit =
        { onAction(LoginCodeViewAction.InitView(email = email)) }

    @Composable
    override fun provideContent(
        viewState: LoginCodeViewState,
        onAction: (LoginCodeViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BL(viewState.email)

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.code,
                onValueChange = { onAction(LoginCodeViewAction.ChangeCode(code = it)) },
                label = stringResource(resource = Res.string.code),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_in_with_code),
                onClick = { onAction(LoginCodeViewAction.SignInWithCode) }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun LoginCodeScreenContentPreview() {
    LoginCodeScreen(
        email = "email@example.com",
    ).PreviewContent(
        LoginCodeViewState()
    )
}
// endregion