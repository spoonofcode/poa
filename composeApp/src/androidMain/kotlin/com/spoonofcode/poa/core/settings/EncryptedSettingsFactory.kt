package com.spoonofcode.poa.core.settings

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

lateinit var appContext: Context

actual fun createEncryptedSettings(): Settings {
    val fileName = "poa-settings"
    val masterKey = MasterKey.Builder(appContext)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    val sharedPreferences = try {
        createSharedPrefs(appContext, fileName, masterKey)
    } catch (e: Exception) {
        appContext.deleteSharedPreferences(fileName)
        createSharedPrefs(appContext, fileName, masterKey)
    }

    return SharedPreferencesSettings(sharedPreferences)
}

private fun createSharedPrefs(context: Context, fileName: String, masterKey: MasterKey) =
    EncryptedSharedPreferences.create(
        context,
        fileName,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
