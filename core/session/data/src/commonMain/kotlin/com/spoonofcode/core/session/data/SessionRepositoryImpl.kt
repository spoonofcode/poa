package com.spoonofcode.core.session.data

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.core.session.domain.SessionRepository
import eu.anifantakis.lib.ksafe.KSafe
import kotlinx.coroutines.withContext

class SessionRepositoryImpl(
    private val ksafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) : SessionRepository {

    override suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override suspend fun initSession(
        accessToken: String,
        refreshToken: String,
        userId: String
    ) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Session.ACCESS_TOKEN, accessToken)
            ksafe.put(Keys.Session.REFRESH_TOKEN, refreshToken)
            ksafe.put(Keys.Session.USER_ID, userId)
        }
    }

    override suspend fun updateSessionTokens(
        accessToken: String,
        refreshToken: String
    ) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Session.ACCESS_TOKEN, accessToken)
            ksafe.put(Keys.Session.REFRESH_TOKEN, refreshToken)
        }
    }

    override suspend fun clearSession() {
        withContext(dispatcherProvider.io()) {
            ksafe.clearAll()
        }
    }

    override suspend fun getSessionAccessToken(): String? =
        withContext(dispatcherProvider.io()) {
            ksafe.get<String?>(Keys.Session.ACCESS_TOKEN, null)
        }

    override suspend fun getSessionRefreshToken(): String? =
        withContext(dispatcherProvider.io()) {
            ksafe.get<String?>(Keys.Session.REFRESH_TOKEN, null)
        }

    override suspend fun getSessionUserId(): String =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                ksafe.get<String?>(Keys.Session.USER_ID, null)
            ) { "User ID cannot be null" }
        }

    private object Keys {
        private const val BASE_PREFIX = "com.spoonofcode"

        object Session {
            private const val PREFIX = "$BASE_PREFIX.session"
            const val ACCESS_TOKEN = "$PREFIX.access_token"
            const val REFRESH_TOKEN = "$PREFIX.refresh_token"
            const val USER_ID = "$PREFIX.user_id"
        }
    }
}