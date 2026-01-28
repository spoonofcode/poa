package com.spoonofcode.core.data.settings

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings

class IosSettingsFactory : Settings.Factory {
    @OptIn(ExperimentalSettingsImplementation::class)
    override fun create(name: String?): Settings = KeychainSettings(service = name ?: "poa_keychain_service")
}
