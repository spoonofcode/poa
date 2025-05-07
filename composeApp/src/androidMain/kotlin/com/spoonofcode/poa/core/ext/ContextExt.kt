package com.spoonofcode.poa.com.spoonofcode.poa.core.ext

import android.content.Context
import java.util.Locale

fun Context.setLocale(locale: Locale): Context {
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    return createConfigurationContext(config)
}