package com.spoonofcode.poa.core.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.KeychainSettings

actual fun createEncryptedSettings(): Settings {
    return KeychainSettings(service = "dojo_pro_keychain_service")
}