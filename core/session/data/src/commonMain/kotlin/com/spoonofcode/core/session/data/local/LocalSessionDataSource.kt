package com.spoonofcode.core.session.data.local

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import eu.anifantakis.lib.ksafe.KSafe
import kotlinx.coroutines.withContext

class LocalSessionDataSource(
    private val kSafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun initSession(
        accessToken: String,
        refreshToken: String,
        userId: String,
        userEmail: String,
    ) {
        withContext(dispatcherProvider.io()) {
            kSafe.clearAll()
            kSafe.put(Keys.Session.ACCESS_TOKEN, accessToken)
            kSafe.put(Keys.Session.REFRESH_TOKEN, refreshToken)
            kSafe.put(Keys.Session.USER_ID, userId)
            kSafe.put(Keys.Session.USER_EMAIL, userEmail)
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

    suspend fun getSessionUserEmail(): String =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                kSafe.get<String?>(Keys.Session.USER_EMAIL, null)
            ) { "User Email cannot be null" }
        }

    private object Keys {
        private const val PREFIX = "com.spoonofcode.core.session"

        object Session {
            const val ACCESS_TOKEN = "$PREFIX.access_token"
            const val REFRESH_TOKEN = "$PREFIX.refresh_token"
            const val USER_ID = "$PREFIX.user_id"
            const val USER_EMAIL = "$PREFIX.user_email"
        }
    }
}