package com.spoonofcode.feature.tac.data.local

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.core.logging.POALogger
import com.spoonofcode.feature.tac.domain.model.TACSettings
import eu.anifantakis.lib.ksafe.KSafe
import eu.anifantakis.lib.ksafe.KSafeWriteMode
import kotlinx.coroutines.withContext

internal class LocalTACDataSource(
    private val kSafe: KSafe,
    private val dispatcherProvider: DispatcherProvider,
    private val poaLogger: POALogger,
) {
    suspend fun getTACSettings(): TACSettings =
        withContext(dispatcherProvider.io()) {
            poaLogger.debug("LocalTACDataSource: Fetching TAC settings from local storage")
            TACSettings(
                isPrivacyPolicyAccepted =
                    kSafe.get(
                        key = Keys.Settings.PRIVACY_POLICY_ACCEPTED,
                        defaultValue = false,
                    ),
                isTermsAndConditionsAccepted =
                    kSafe.get(
                        key = Keys.Settings.TERMS_AND_CONDITIONS_ACCEPTED,
                        defaultValue = false,
                    ),
                privacyPolicyLink = PRIVACY_POLICY_LINK,
                marketingLink = MARKETING_LINK,
            )
        }

    suspend fun setTACSettings(
        isPrivacyPolicyAccepted: Boolean,
        isTermsAndConditionsAccepted: Boolean,
    ) {
        withContext(dispatcherProvider.io()) {
            poaLogger.info("LocalTACDataSource: Saving TAC settings to local storage")
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

    companion object {
        private const val PRIVACY_POLICY_LINK = "https://proof-of-wear.com/privacy-mobile.html"
        private const val MARKETING_LINK = "https://proof-of-wear.com/marketing-mobile.html"
    }
}
