package com.spoonofcode.poa.feature.login.register

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
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class RegisterScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.register,
) : BaseScreen<RegisterViewModel, RegisterViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<RegisterViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: RegisterViewModel,
        viewState: RegisterViewState,
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            changeEmail = { viewModel.changeEmail(it) },
            changePassword = { viewModel.changePassword(it) },
            changeFirstName = { viewModel.changeFirstName(it) },
            changeLastName = { viewModel.changeLastName(it) },
            signUp = { viewModel.signUp() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: RegisterViewState,
        changeEmail: (String) -> Unit,
        changePassword: (String) -> Unit,
        changeFirstName: (String) -> Unit,
        changeLastName: (String) -> Unit,
        signUp: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
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

            TextFields.Outlined(
                value = viewState.firstName,
                onValueChange = { changeFirstName(it) },
                label = stringResource(resource = Res.string.first_name),
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.lastName,
                onValueChange = { changeLastName(it) },
                label = stringResource(resource = Res.string.last_name),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_up),
                onClick = { signUp() }
            )
        }
    }
}