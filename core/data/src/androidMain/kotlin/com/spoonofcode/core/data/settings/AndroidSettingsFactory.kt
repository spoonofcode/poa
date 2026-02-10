package com.spoonofcode.core.data.settings

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

class AndroidSettingsFactory(private val context: Context) : Settings.Factory {
    override fun create(name: String?): Settings {
        val fileName = name ?: "poa-settings"
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val sharedPreferences = try {
            createSharedPrefs(context, fileName, masterKey)
        } catch (e: Exception) {
            context.deleteSharedPreferences(fileName)
            createSharedPrefs(context, fileName, masterKey)
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
}
