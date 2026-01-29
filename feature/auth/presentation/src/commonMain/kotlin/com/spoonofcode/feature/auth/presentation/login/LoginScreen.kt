package com.spoonofcode.feature.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Dividers
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.email
import com.spoonofcode.core.presentation.let_s_get_started
import com.spoonofcode.core.presentation.or
import com.spoonofcode.core.presentation.poa
import com.spoonofcode.core.presentation.sign_in
import org.jetbrains.compose.resources.stringResource

internal class LoginScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<LoginViewModel, LoginViewState, LoginViewAction>() {

    @Composable
    override fun provideViewModel() = koinViewModel<LoginViewModel>()

    @Composable
    override fun provideContent(
        viewState: LoginViewState,
        onAction: (LoginViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Column(
                modifier = Modifier.fillMaxSize().padding(
                    top = Paddings.screenPadding,
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Images.CircleImage(imageRes = Res.drawable.poa)
                Spacers.VerticalBetweenFields()
                Texts.HL(stringResource(resource = Res.string.let_s_get_started))
            }

            TextFields.Outlined(
                value = viewState.email,
                onValueChange = { onAction(LoginViewAction.ChangeEmail(email = it)) },
                label = stringResource(resource = Res.string.email),
            )

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_in),
                enabled = viewState.isSignInButtonEnable,
                onClick = { onAction(LoginViewAction.SignInWithCode) }
            )

            Dividers.LabeledDivider(
                text = stringResource(resource = Res.string.or)
            )

            Buttons.GoogleSignInButton(onGoogleSignInResult = { googleAccount ->
                onAction(LoginViewAction.SignInWithGoogle(googleAccount =googleAccount))
            })
        }
    }
}

// region previews
@Preview
@Composable
private fun LoginScreenContentPreview() {
    LoginScreen().PreviewContent(
        LoginViewState()
    )
}
// endregion