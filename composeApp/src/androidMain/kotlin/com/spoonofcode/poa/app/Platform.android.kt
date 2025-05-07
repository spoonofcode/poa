package com.spoonofcode.poa.app

import android.os.Build
import com.spoonofcode.poa.app.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()