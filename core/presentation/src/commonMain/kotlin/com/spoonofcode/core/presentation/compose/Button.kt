package com.spoonofcode.core.presentation.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.core.presentation.ext.addIf
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    leftIcon: DrawableResource? = null,
    enabled: Boolean = true,
    fillMaxWidth: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
): Unit = Button(
    modifier = modifier
        .addIf(condition = fillMaxWidth) { fillMaxWidth() }
        .heightIn(min = 48.dp),
    shape = MaterialTheme.shapes.medium,
    enabled = enabled && !isLoading,
    onClick = onClick,
) {
    AnimatedContent(
        targetState = isLoading,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "ButtonLoadingTransition"
    ) { loading ->
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 2.5.dp
            )
        } else {
            if (leftIcon != null) {
                Image(
                    painter = painterResource(resource = leftIcon),
                    contentDescription = null,
                )
                SpacerHorizontalBetweenFields()
            }

            Text(text)
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    leftIcon: DrawableResource? = null,
    colors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    isLoading: Boolean = false,
    onClick: () -> Unit,
): Unit =
    FilledTonalButton(
        modifier = modifier.fillMaxWidth()
            .heightIn(min = 48.dp),
        shape = MaterialTheme.shapes.medium,
        colors = colors,
        onClick = onClick,
    ) {
        AnimatedContent(
            targetState = isLoading,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "ButtonLoadingTransition"
        ) { loading ->
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.5.dp
                )
            } else {
                if (leftIcon != null) {
                    Image(
                        painter = painterResource(resource = leftIcon),
                        contentDescription = null,
                    )
                    SpacerHorizontalBetweenFields()
                }

                Text(text)
            }
        }
    }

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    leftIcon: DrawableResource? = null,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    isLoading: Boolean = false,
    onClick: () -> Unit,
): Unit =
    androidx.compose.material3.OutlinedButton(
        modifier = modifier.fillMaxWidth()
            .heightIn(min = 48.dp),
        shape = MaterialTheme.shapes.medium,
        colors = colors,
        onClick = onClick,
    ) {
        AnimatedContent(
            targetState = isLoading,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "ButtonLoadingTransition"
        ) { loading ->
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.5.dp
                )
            } else {
                if (leftIcon != null) {
                    Image(
                        painter = painterResource(resource = leftIcon),
                        contentDescription = null,
                    )
                    SpacerHorizontalBetweenFields()
                }

                Text(text)
            }
        }
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
