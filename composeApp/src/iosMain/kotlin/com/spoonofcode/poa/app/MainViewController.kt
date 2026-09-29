package com.spoonofcode.poa.app

import androidx.compose.ui.window.ComposeUIViewController
import com.spoonofcode.core.presentation.compose.setupIosCoilImageLoader

fun MainViewController() = ComposeUIViewController(
    configure = {
        setupIosCoilImageLoader()
        KoinInitializer().init()
    },
) { App(notificationId = null) }
