package com.spoonofcode.core.presentation.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

object Texts {
    @Composable
    fun DS(
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
    fun DSB(
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
    fun DM(
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
    fun DMB(
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
    fun DL(
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
    fun DLB(
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
    fun TS(
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
    fun TSB(
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
    fun TM(
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
    fun TMB(
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
    fun TL(
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
    fun TLB(
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
    fun HS(
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
    fun HSB(
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
    fun HM(
        text: String,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
    }

    @Composable
    fun HMB(
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
    fun HL(
        text: String,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge
        )
    }

    @Composable
    fun HLB(
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
    fun BS(
        text: String,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )
    }

    @Composable
    fun BSB(
        text: String,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
        )
    }

    @Composable
    fun BM(
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
    fun BMB(
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
    fun BL(
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
    fun BLB(
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
    fun LS(
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
    fun LSB(
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
    fun LM(
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
    fun LMB(
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
    fun LL(
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
    fun LLB(
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
}