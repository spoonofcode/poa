package com.spoonofcode.poa.feature.login.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Dividers
import com.spoonofcode.poa.core.ui.compose.Images
import com.spoonofcode.poa.core.ui.compose.Paddings
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.email
import com.spoonofcode.poa.resources.let_s_get_started
import com.spoonofcode.poa.resources.or
import com.spoonofcode.poa.resources.poa
import com.spoonofcode.poa.resources.sign_in
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