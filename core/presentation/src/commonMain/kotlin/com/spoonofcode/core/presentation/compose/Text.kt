package com.spoonofcode.core.presentation.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TextDS(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.displaySmall,
    )
}

@Composable
fun TextDSB(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextDM(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.displayMedium,
    )
}

@Composable
fun TextDMB(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextDL(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.displayLarge,
    )
}

@Composable
fun TextDLB(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.displayLarge,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextTS(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleSmall,
    )
}

@Composable
fun TextTSB(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextTM(
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    color: Color = Color.Unspecified,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow,
        color = color,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
fun TextTMB(
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    color: Color = Color.Unspecified,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        minLines = minLines,
        overflow = overflow,
        color = color,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextTL(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
fun TextTLB(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextHS(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
fun TextHSB(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextHM(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun TextHMB(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextHL(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
fun TextHLB(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextBS(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
fun TextBSB(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextBM(
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        color = color,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun TextBMB(
    text: String,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextBL(
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
fun TextBLB(
    text: String,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextLS(
    text: String,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
fun TextLSB(
    text: String,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        color = color,
        maxLines = maxLines,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextLM(
    text: String,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.labelMedium,
    )
}

@Composable
fun TextLMB(
    text: String,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextLL(
    text: String,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.labelLarge,
    )
}

@Composable
fun TextLLB(
    text: String,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
    )
}
