package com.spoonofcode.poa.feature.login.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Buttons.GoogleSignInButton
import com.spoonofcode.poa.core.ui.compose.CircleImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.email
import com.spoonofcode.poa.resources.forget_password
import com.spoonofcode.poa.resources.let_s_get_started
import com.spoonofcode.poa.resources.password
import com.spoonofcode.poa.resources.poa
import com.spoonofcode.poa.resources.sign_in
import com.spoonofcode.poa.resources.sign_up
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class LoginScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<LoginViewModel, LoginViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<LoginViewModel>()

    @Composable
    override fun provideContent(
        viewModel: LoginViewModel?,
        viewState: LoginViewState
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        changeEmail = { viewModel?.changeEmail(it) },
        changePassword = { viewModel?.changePassword(it) },
        forgotPassword = { viewModel?.forgotPassword() },
        signIn = { viewModel?.signIn() },
        signInWithGoogle = { viewModel?.signInWithGoogle(it) },
        signUp = { viewModel?.signUp() },
    )

    @Composable
    internal fun ContentView(
        viewState: LoginViewState,
        changeEmail: (String) -> Unit,
        changePassword: (String) -> Unit,
        forgotPassword: () -> Unit,
        signIn: () -> Unit,
        signInWithGoogle: (String) -> Unit,
        signUp: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircleImage(imageRes = Res.drawable.poa)
                Spacers.VerticalBetweenFields()
                Texts.HL(stringResource(resource = Res.string.let_s_get_started))
            }

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.email,
                onValueChange = { changeEmail(it) },
                label = stringResource(resource = Res.string.email),
            )

            Spacers.VerticalBetweenFields()

            TextFields.OutlinedPassword(
                value = viewState.password,
                onValueChange = { changePassword(it) },
                label = stringResource(resource = Res.string.password),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_in),
                onClick = { signIn() }
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.forget_password),
                onClick = { forgotPassword() }
            )

            Spacers.VerticalBetweenFields()

            GoogleSignInButton(onGoogleSignInResult = { googleUser ->
                // send Google id token to your server
                val googleUserToken = requireNotNull(googleUser?.token)
                println("BARTEK googleUserToken = $googleUserToken")
                signInWithGoogle(googleUserToken)
            })

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_up),
                onClick = { signUp() }
            )
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