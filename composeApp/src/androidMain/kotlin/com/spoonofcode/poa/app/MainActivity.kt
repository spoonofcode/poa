package com.spoonofcode.poa.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.spoonofcode.core.data.network.initUrlOpener

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUrlOpener(applicationContext)
        requestNotificationPermission()

        // Check or we navigate from Notification
        val notificationId = intent.getStringExtra(NOTIFICATION_ID_KEY)

        installSplashScreen()
        setContent {
            App(notificationId)
        }
    }

    private fun requestNotificationPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            val hasPermission =
                checkSelfPermission(
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) == android.content.pm.PackageManager.PERMISSION_GRANTED

            if (hasPermission.not()) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0)
            }
        }
    }

    private companion object {
        private const val NOTIFICATION_ID_KEY = "notificationId"
    }
}