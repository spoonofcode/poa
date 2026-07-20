package com.spoonofcode.feature.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Buttons.PrimaryButton
import com.spoonofcode.core.presentation.compose.Dividers
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.auth.presentation.Res
import com.spoonofcode.feature.auth.presentation.email
import com.spoonofcode.feature.auth.presentation.ic_google
import com.spoonofcode.feature.auth.presentation.let_s_get_started
import com.spoonofcode.feature.auth.presentation.model.GoogleAccount
import com.spoonofcode.feature.auth.presentation.or
import com.spoonofcode.feature.auth.presentation.sign_in
import com.spoonofcode.feature.auth.presentation.sign_in_with_google
import com.spoonofcode.feature.auth.presentation.taggy_go_logo
import com.spoonofcode.feature.auth.presentation.utils.GoogleAuthProvider
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

internal class LoginScreen(
    override val backNavigationEnable: Boolean = false,
    val viewModel: LoginViewModel,
) : BaseScreen<LoginViewModel, LoginViewState, LoginViewAction, Nothing>() {

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
                Images.CircleImage(imageRes = Res.drawable.taggy_go_logo)
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

            GoogleSignInButton(onGoogleSignInResult = { googleAccount ->
                onAction(LoginViewAction.SignInWithGoogle(googleAccount = googleAccount))
            })
        }
    }
}

@Composable
private fun GoogleSignInButton(
    onGoogleSignInResult: (GoogleAccount?) -> Unit,
) {
    val googleAuthProvider = koinInject<GoogleAuthProvider>()
    val googleAuthUiProvider = googleAuthProvider.getUiProvider()

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = googleAuthUiProvider.isGoogleAccountAvailable()
    }

    if (isVisible) {
        val coroutineScope = rememberCoroutineScope()

        Dividers.LabeledDivider(
            text = stringResource(resource = Res.string.or)
        )

        PrimaryButton(
            text = stringResource(resource = Res.string.sign_in_with_google),
            leftIcon = Res.drawable.ic_google,
            onClick = {
                coroutineScope.launch {
                    val googleAccount = googleAuthUiProvider.signIn()
                    onGoogleSignInResult(googleAccount)
                }
            }
        )
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