package com.spoonofcode.core.presentation.ext

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.currentKoinScope

@Composable
inline fun <reified T : ViewModel> Screen.koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}