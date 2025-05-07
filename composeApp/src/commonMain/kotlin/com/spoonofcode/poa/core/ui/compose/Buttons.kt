package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.spoonofcode.poa.core.model.GoogleAccount
import com.spoonofcode.poa.core.ui.utils.GoogleAuthProvider
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.ic_google
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

object Buttons {

    @Composable
    fun PrimaryButton(
        text: String,
        leftIcon: DrawableResource? = null,
        onClick: () -> Unit,
    ): Unit = Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        if (leftIcon != null) {
            Image(
                painter = painterResource(resource = Res.drawable.ic_google),
                contentDescription = null,
            )
            Spacers.HorizontalBetweenFields()
        }

        Text(text)
    }

    @Composable
    fun SecondaryButton(
        text: String,
        leftIcon: DrawableResource? = null,
        onClick: () -> Unit,
    ): Unit =
        FilledTonalButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick,
        ) {
            if (leftIcon != null) {
                Image(
                    painter = painterResource(resource = Res.drawable.ic_google),
                    contentDescription = null,
                )
                Spacers.HorizontalBetweenFields()
            }

            Text(text)
        }

    @Composable
    fun GoogleSignInButton(
        onGoogleSignInResult: (GoogleAccount?) -> Unit,
    ) {
        val googleAuthProvider = koinInject<GoogleAuthProvider>()
        val googleAuthUiProvider = googleAuthProvider.getUiProvider()
        val coroutineScope = rememberCoroutineScope()
        PrimaryButton(
            text = "Sign in with Google",
            leftIcon = Res.drawable.ic_google,
            onClick = {
                coroutineScope.launch {
                    val googleUser = googleAuthUiProvider.signIn()
                    onGoogleSignInResult(googleUser)
                }
            }
        )
    }

    @Composable
    fun GoogleSignOutButton() {
        val coroutineScope = rememberCoroutineScope()
        val googleAuthProvider = koinInject<GoogleAuthProvider>()
        val googleAuthUiProvider = googleAuthProvider.getUiProvider()

        PrimaryButton(
            text = "Sign out with Google",
            leftIcon = Res.drawable.ic_google,
            onClick = {
                coroutineScope.launch {
                    googleAuthUiProvider.signOut()
                }
            }
        )
    }

}