package com.spoonofcode.feature.auth.presentation.logincode

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.feature.auth.presentation.Res
import com.spoonofcode.feature.auth.presentation.code
import com.spoonofcode.feature.auth.presentation.one_time_code_info_postfix
import com.spoonofcode.feature.auth.presentation.one_time_code_info_prefix
import com.spoonofcode.feature.auth.presentation.sign_in_with_code
import org.jetbrains.compose.resources.stringResource

private const val VERIFICATION_CODE_LENGTH = 6

@Composable
internal fun LoginCodeScreen(
    viewModel: LoginCodeViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        topBarTitle = stringResource(Res.string.sign_in_with_code),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: LoginCodeViewState,
    onAction: (LoginCodeViewAction) -> Unit,
) {
    Text(
        text = buildAnnotatedString {
            append(stringResource(resource = Res.string.one_time_code_info_prefix))
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append(viewState.email)
            }
            append(stringResource(resource = Res.string.one_time_code_info_postfix))
        },
        style = MaterialTheme.typography.bodyLarge,
    )

    TextFields.OutlinedPassword(
        value = viewState.code,
        onValueChange = { newValue ->
            if (newValue.length <= VERIFICATION_CODE_LENGTH) {
                onAction(LoginCodeViewAction.ChangeCode(code = newValue))
            } else {
                // ignore
            }
        },
        label = stringResource(resource = Res.string.code),
    )

    Buttons.PrimaryButton(
        text = stringResource(resource = Res.string.sign_in_with_code),
        enabled = viewState.isSignInWithCodeButtonEnable,
        onClick = { onAction(LoginCodeViewAction.SignInWithCode) }
    )
}

// region previews
//@Preview
//@Composable
//private fun LoginCodeScreenContentPreview() {
//    LoginCodeScreen(
//        email = "email@example.com",
//    ).PreviewContent(
//        LoginCodeViewState()
//    )
//}
// endregion
