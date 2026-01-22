package com.spoonofcode.poa.core.settings

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.Settings
import com.russhwolf.settings.KeychainSettings

@OptIn(ExperimentalSettingsImplementation::class)
actual fun createEncryptedSettings(): Settings = KeychainSettings(service = "poa_keychain_service")