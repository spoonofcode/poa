package com.spoonofcode.poa.core.settings

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

// You might inject or retrieve 'context' differently in your actual project.
lateinit var appContext: Context

actual fun createEncryptedSettings(): Settings {
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    val sharedPreferences = EncryptedSharedPreferences.create(
        "poa-settings",
        masterKeyAlias,
        appContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    return SharedPreferencesSettings(sharedPreferences)
}