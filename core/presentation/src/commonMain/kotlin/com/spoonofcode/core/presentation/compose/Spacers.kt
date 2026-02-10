package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object Spacers {

    @Composable
    fun VerticalBetweenFields(): Unit = Spacer(modifier = Modifier.height(Paddings.fieldsPadding))

    @Composable
    fun HorizontalBetweenFields(): Unit = Spacer(modifier = Modifier.width(Paddings.fieldsPadding))

    @Composable
    fun Weight1(rowScope: RowScope): Unit =
        with(rowScope) { Spacer(modifier = Modifier.weight(1f)) }

    @Composable
    fun Weight1(columnScope: ColumnScope): Unit =
        with(columnScope) { Spacer(modifier = Modifier.weight(1f)) }
}