package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.search
import org.jetbrains.compose.resources.stringResource

@Composable
fun OutlinedTextFieldStandard(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isRequired: Boolean = false,
    label: String? = null,
    innerLabel: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(
        modifier = modifier
    ) {
        label?.let { labelText ->
            Text(
                text = buildAnnotatedString {
                    append(labelText)
                    if (isRequired) {
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.error)) {
                            append(" *")
                        }
                    }
                },
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            label = innerLabel?.let { labelText ->
                {
                    Text(text = buildAnnotatedString {
                        append(labelText)
                        if (isRequired) {
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.error)) {
                                append(" *")
                            }
                        }
                    })
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            minLines = minLines,
            visualTransformation = visualTransformation,
            shape = MaterialTheme.shapes.large,
            singleLine = minLines == 1,
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
    }
}

@Composable
fun OutlinedTextFieldLong(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    innerLabel: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    label?.let {
        TextBLB(text = it)
    }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        label = { innerLabel?.let { Text(text = it) } },
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        shape = MaterialTheme.shapes.large,
        singleLine = false,
        minLines = 4,
        maxLines = 4,
    )
    SpacerVerticalBetweenFields()
}

@Composable
fun OutlinedTextFieldPassword(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
) {
    // Tracks if the password is currently visible
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextFieldStandard(
        value = value,
        onValueChange = onValueChange,
        label = label,
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) {
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    },
                    contentDescription = if (passwordVisible) {
                        "Hide password"
                    } else {
                        "Show password"
                    }
                )
            }
        },
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
    )
}

@Composable
fun OutlinedTextFieldSearch(
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextFieldStandard(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        innerLabel = stringResource(resource = Res.string.search),
    )
}
