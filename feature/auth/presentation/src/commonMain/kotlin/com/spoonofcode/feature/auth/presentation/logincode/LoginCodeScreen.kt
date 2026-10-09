package com.spoonofcode.feature.auth.presentation.logincode

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.OutlinedTextFieldPassword
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.feature.auth.presentation.Res
import com.spoonofcode.feature.auth.presentation.code
import com.spoonofcode.feature.auth.presentation.one_time_code_info_postfix
import com.spoonofcode.feature.auth.presentation.one_time_code_info_prefix
import com.spoonofcode.feature.auth.presentation.sign_in_with_code
import org.jetbrains.compose.resources.stringResource

private const val VERIFICATION_CODE_LENGTH = 6

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.sign_in_with_code),
)

@Composable
internal fun LoginCodeScreen(
    viewModel: LoginCodeViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
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

    OutlinedTextFieldPassword(
        value = viewState.code,
        onValueChange = { newValue ->
            if (newValue.length <= VERIFICATION_CODE_LENGTH) {
                onAction(LoginCodeViewAction.ChangeCode(code = newValue))
            } else {
                // ignore
            }
        },
        isRequired = true,
        label = stringResource(resource = Res.string.code),
    )

    PrimaryButton(
        text = stringResource(resource = Res.string.sign_in_with_code),
        enabled = viewState.isSignInWithCodeButtonEnable,
        isLoading = viewState.isSignInWithCodeButtonLoading,
        onClick = { onAction(LoginCodeViewAction.SignInWithCode) }
    )
}

// region previews
@Preview
@Composable
private fun LoginCodeScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(LoginCodeViewState(email = "email@example.com")),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
