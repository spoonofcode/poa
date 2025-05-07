package com.spoonofcode.poa.feature.login.forgotPassword

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.email
import com.spoonofcode.poa.resources.forget_password
import com.spoonofcode.poa.resources.sign_up
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class ForgotPasswordScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.forget_password,
) : BaseScreen<ForgotPasswordViewModel, ForgotPasswordViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<ForgotPasswordViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ForgotPasswordViewModel,
        viewState: ForgotPasswordViewState,
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            changeEmail = { viewModel.changeEmail(it) },
            resetPassword = { viewModel.resetPassword() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: ForgotPasswordViewState,
        changeEmail: (String) -> Unit,
        resetPassword: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.email,
                onValueChange = { changeEmail(it) },
                label = stringResource(resource = Res.string.email),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_up),
                onClick = { resetPassword() }
            )
        }
    }
}