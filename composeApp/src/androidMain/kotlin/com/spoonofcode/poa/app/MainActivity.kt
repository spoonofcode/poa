package com.spoonofcode.poa.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.spoonofcode.core.presentation.web.initUrlOpener

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUrlOpener(applicationContext)

        // Check or we navigate from Notification
        val notificationId = intent.getStringExtra(NOTIFICATION_ID_KEY)

        installSplashScreen()
        setContent {
            App(notificationId)
        }
    }

    private companion object {
        private const val NOTIFICATION_ID_KEY = "notificationId"
    }
}