package com.spoonofcode.core.data.network

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

private lateinit var androidContext: Context

fun initUrlOpener(context: Context) {
    androidContext = context.applicationContext
}

class AndroidUrlOpener : UrlOpener {
    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        androidContext.startActivity(intent)
    }
}

actual fun getUrlOpener(): UrlOpener = AndroidUrlOpener()