package com.spoonofcode.poa.app

import androidx.compose.runtime.Composable

@Composable
expect fun PlatformBackHandlerSupport(content: @Composable () -> Unit)
