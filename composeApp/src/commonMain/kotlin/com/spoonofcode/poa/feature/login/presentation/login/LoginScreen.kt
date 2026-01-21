package com.spoonofcode.poa.feature.login.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Paddings
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
import com.spoonofcode.poa.resources.poa
import com.spoonofcode.poa.resources.sign_in
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview

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
                CircleImage(imageRes = Res.drawable.poa)
                Spacers.VerticalBetweenFields()
                Texts.HL(stringResource(resource = Res.string.let_s_get_started))
            }

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.email,
                onValueChange = { onAction(LoginViewAction.ChangeEmail(email = it)) },
                label = stringResource(resource = Res.string.email),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_in),
                onClick = { onAction(LoginViewAction.SignInWithCode) }
            )

            Spacers.VerticalBetweenFields()

            LabeledDivider()

            Spacers.VerticalBetweenFields()

            GoogleSignInButton(onGoogleSignInResult = { googleUser ->
                // send Google id token to your server
                val googleUserToken = requireNotNull(googleUser?.token)
                onAction(LoginViewAction.SignInWithGoogle(googleUserToken))
            })

            Spacers.VerticalBetweenFields()
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