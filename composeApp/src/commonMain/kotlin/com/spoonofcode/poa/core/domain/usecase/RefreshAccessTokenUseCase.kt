package com.spoonofcode.poa.core.domain.usecase

import com.spoonofcode.poa.core.domain.RefreshDataSource
import com.spoonofcode.poa.core.network.SessionManager

class RefreshAccessTokenUseCase(
    private val refreshDataSource: RefreshDataSource,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke() : Boolean {
        val jwtRefreshToken = sessionManager.getSessionRefreshToken() ?: return false
        val refresh = refreshDataSource.refreshToken(jwtRefreshToken)
        sessionManager.saveSessionAccessToken(refresh.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(refresh.jwtRefreshToken)
        return true
    }
}