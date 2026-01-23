package com.spoonofcode.poa.feature.login.presentation.logincode

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.code
import com.spoonofcode.poa.resources.one_time_code_info_postfix
import com.spoonofcode.poa.resources.one_time_code_info_prefix
import com.spoonofcode.poa.resources.sign_in_with_code
import org.jetbrains.compose.resources.stringResource

internal class LoginCodeScreen(
    val email: String,
) : BaseScreen<LoginCodeViewModel, LoginCodeViewState, LoginCodeViewAction>() {

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
            Text(
                text = buildAnnotatedString {
                    append(stringResource(resource = Res.string.one_time_code_info_prefix))
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(viewState.email)
                    }
                    append(stringResource(resource = Res.string.one_time_code_info_postfix))
                }
            )

            Spacers.VerticalBetweenFields()

            TextFields.OutlinedPassword(
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