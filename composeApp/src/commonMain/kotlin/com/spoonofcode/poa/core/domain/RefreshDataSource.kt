package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.domain.model.Refresh

interface RefreshDataSource {
    suspend fun refreshToken(jwtRefreshToken: String): Refresh
}