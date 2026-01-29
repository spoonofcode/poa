package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.core.presentation.domain.model.GoogleAccount
import com.spoonofcode.core.presentation.ext.addIf
import com.spoonofcode.core.presentation.utils.GoogleAuthProvider
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.ic_google
import com.spoonofcode.core.presentation.sign_in_with_google

object Buttons {

    @Composable
    fun PrimaryButton(
        modifier: Modifier = Modifier,
        text: String,
        leftIcon: DrawableResource? = null,
        enabled: Boolean = true,
        fillMaxWidth: Boolean = true,
        onClick: () -> Unit,
    ): Unit = Button(
        modifier = modifier
            .addIf(condition = fillMaxWidth) { fillMaxWidth() }
            .heightIn(min = 48.dp),
        shape = MaterialTheme.shapes.medium,
        enabled = enabled,
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
            modifier = Modifier.fillMaxWidth()
                .heightIn(min = 48.dp),
            shape = MaterialTheme.shapes.medium,
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

    @Composable
    fun IconTextButton(
        modifier: Modifier = Modifier,
        icon: ImageVector,
        text: String,
        onClick: () -> Unit,
        square: Boolean = true,
        enabled: Boolean = true,
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .then(
                    if (square) Modifier.aspectRatio(1f) else Modifier
                ),
            shape = MaterialTheme.shapes.small,
            contentPadding = PaddingValues(8.dp),
            enabled = enabled,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = text,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = text,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}