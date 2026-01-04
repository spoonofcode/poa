package com.spoonofcode.poa.feature.login.presentation.register

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.email
import com.spoonofcode.poa.resources.first_name
import com.spoonofcode.poa.resources.last_name
import com.spoonofcode.poa.resources.password
import com.spoonofcode.poa.resources.register
import com.spoonofcode.poa.resources.sign_up
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class RegisterScreen() : BaseScreen<RegisterViewModel, RegisterViewState, RegisterViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.register

    @Composable
    override fun provideViewModel() = koinViewModel<RegisterViewModel>()

    @Composable
    override fun provideContent(
        viewState: RegisterViewState,
        onAction: (RegisterViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.email,
                onValueChange = { onAction(RegisterViewAction.ChangeEmail(it)) },
                label = stringResource(resource = Res.string.email),
            )

            Spacers.VerticalBetweenFields()

            TextFields.OutlinedPassword(
                value = viewState.password,
                onValueChange = { onAction(RegisterViewAction.ChangePassword(it)) },
                label = stringResource(resource = Res.string.password),
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.firstName,
                onValueChange = { onAction(RegisterViewAction.ChangeFirstName(it)) },
                label = stringResource(resource = Res.string.first_name),
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.lastName,
                onValueChange = { onAction(RegisterViewAction.ChangeLastName(it)) },
                label = stringResource(resource = Res.string.last_name),
            )

            Spacers.VerticalBetweenFields()

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_up),
                onClick = { onAction(RegisterViewAction.SignUp) }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun RegisterScreenContentPreview() {
    RegisterScreen().PreviewContent(
        RegisterViewState()
    )
}
// endregion