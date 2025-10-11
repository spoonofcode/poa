package com.spoonofcode.poa.feature.login.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Buttons.GoogleSignInButton
import com.spoonofcode.poa.core.ui.compose.CircleImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.email
import com.spoonofcode.poa.resources.let_s_get_started
import com.spoonofcode.poa.resources.or
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
                modifier = Modifier.fillMaxSize().padding(
                    top = Paddings.screenPadding,
                ),
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

            ForgotPasswordInline(onResetClick = { forgotPassword() })

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_in),
                onClick = { signIn() }
            )

            Spacers.VerticalBetweenFields()

            LabeledDivider()

            Spacers.VerticalBetweenFields()

            GoogleSignInButton(onGoogleSignInResult = { googleUser ->
                // send Google id token to your server
                val googleUserToken = requireNotNull(googleUser?.token)
                println("BARTEK googleUserToken = $googleUserToken")
                signInWithGoogle(googleUserToken)
            })

            Spacers.VerticalBetweenFields()

            Buttons.SecondaryButton(
                text = stringResource(resource = Res.string.sign_up),
                onClick = { signUp() }
            )
        }
    }

    @Composable
    fun LabeledDivider(
        text: String = stringResource(resource = Res.string.or),
        modifier: Modifier = Modifier,
        lineColor: Color = MaterialTheme.colorScheme.outlineVariant,
        textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        thickness: Dp = 1.dp,
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = thickness,
                color = lineColor
            )

            Text(
                text = text,
                modifier = Modifier.padding(vertical = 2.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = thickness,
                color = lineColor
            )
        }
    }

    @Composable
    fun ForgotPasswordInline(
        onResetClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        val linkTag = "RESET"

        val text = buildAnnotatedString {
            append("Forgot your password? ")
            pushStringAnnotation(tag = linkTag, annotation = "reset")
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Reset your password")
            }
            pop()
        }

        ClickableText(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            onClick = { offset ->
                text.getStringAnnotations(linkTag, start = offset, end = offset)
                    .firstOrNull()?.let { onResetClick() }
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