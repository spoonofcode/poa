package com.spoonofcode.feature.tac.data.local

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.feature.tac.domain.model.TACSettings
import eu.anifantakis.lib.ksafe.KSafe
import eu.anifantakis.lib.ksafe.KSafeWriteMode
import kotlinx.coroutines.withContext

class LocalTACDataSource(
    private val kSafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun getTACSettings(): TACSettings {
        return withContext(dispatcherProvider.io()) {
            TACSettings(
                isPrivacyPolicyAccepted = kSafe.get(
                    key = Keys.Settings.PRIVACY_POLICY_ACCEPTED,
                    defaultValue = false
                ),
                isTermsAndConditionsAccepted = kSafe.get(
                    key = Keys.Settings.TERMS_AND_CONDITIONS_ACCEPTED,
                    defaultValue = false
                ),
            )
        }
    }

    suspend fun setTACSettings(
        isPrivacyPolicyAccepted: Boolean,
        isTermsAndConditionsAccepted: Boolean,
    ) {
        withContext(dispatcherProvider.io()) {
            kSafe.put(
                key = Keys.Settings.PRIVACY_POLICY_ACCEPTED,
                value = isPrivacyPolicyAccepted,
                mode = KSafeWriteMode.Plain,
            )
            kSafe.put(
                key = Keys.Settings.TERMS_AND_CONDITIONS_ACCEPTED,
                value = isTermsAndConditionsAccepted,
                mode = KSafeWriteMode.Plain,
            )
        }
    }

    private object Keys {
        private const val PREFIX = "com.spoonofcode.feature.tac"

        object Settings {
            private const val SETTINGS_PREFIX = "$PREFIX.settings"
            const val PRIVACY_POLICY_ACCEPTED = "$SETTINGS_PREFIX.privacy_policy_accepted"
            const val TERMS_AND_CONDITIONS_ACCEPTED =
                "$SETTINGS_PREFIX.terms_and_conditions_accepted"
        }
    }
}