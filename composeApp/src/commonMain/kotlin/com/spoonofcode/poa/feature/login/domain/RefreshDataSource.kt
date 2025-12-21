package com.spoonofcode.poa.feature.login.domain

import com.spoonofcode.poa.feature.login.domain.model.Refresh

interface RefreshDataSource {
    suspend fun refreshToken(jwtRefreshToken: String): Refresh
}