package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.spoonofcode.core.presentation.web.getUrlOpener

object Checkboxes {
    @Composable
    fun CheckboxTextLinkRow(
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit,
        text: String,
        linkText: String,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        linkUrl: String,
        isMandatory: Boolean = true,
    ) {
        val annotated =
            buildAnnotatedString {
                pushStringAnnotation(tag = linkText, annotation = "LINK")
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline,
                    )
                ) { append(linkText) }
                pop()
            }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    enabled = enabled,
                    role = Role.Checkbox,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onCheckedChange(!checked) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { onCheckedChange(it) },
                enabled = enabled
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Texts.BM(text = text)
                if (isMandatory) {
                    Text(
                        text = " *",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacers.HorizontalBetweenFields()

            val urlOpener = remember { getUrlOpener() }

            Text(
                text = annotated,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .clickable(onClick = { urlOpener.openUrl(linkUrl) })
            )
        }
    }
}