package com.spoonofcode.core.session.data.local

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import eu.anifantakis.lib.ksafe.KSafe
import kotlinx.coroutines.withContext

class LocalSessionDataSource(
    private val kSafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    suspend fun initSession(
        accessToken: String,
        refreshToken: String,
        userId: String
    ) {
        withContext(dispatcherProvider.io()) {
            kSafe.put(Keys.Session.ACCESS_TOKEN, accessToken)
            kSafe.put(Keys.Session.REFRESH_TOKEN, refreshToken)
            kSafe.put(Keys.Session.USER_ID, userId)
        }
    }

    suspend fun updateSessionTokens(
        accessToken: String,
        refreshToken: String
    ) {
        withContext(dispatcherProvider.io()) {
            kSafe.put(Keys.Session.ACCESS_TOKEN, accessToken)
            kSafe.put(Keys.Session.REFRESH_TOKEN, refreshToken)
        }
    }

    suspend fun clearSession() {
        withContext(dispatcherProvider.io()) {
            kSafe.clearAll()
        }
    }

    suspend fun getSessionAccessToken(): String? =
        withContext(dispatcherProvider.io()) {
            kSafe.get<String?>(Keys.Session.ACCESS_TOKEN, null)
        }

    suspend fun getSessionRefreshToken(): String? =
        withContext(dispatcherProvider.io()) {
            kSafe.get<String?>(Keys.Session.REFRESH_TOKEN, null)
        }

    suspend fun getSessionUserId(): String =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                kSafe.get<String?>(Keys.Session.USER_ID, null)
            ) { "User ID cannot be null" }
        }

    private object Keys {
        private const val PREFIX = "com.spoonofcode.core.session"

        object Session {
            const val ACCESS_TOKEN = "$PREFIX.access_token"
            const val REFRESH_TOKEN = "$PREFIX.refresh_token"
            const val USER_ID = "$PREFIX.user_id"
        }
    }
}