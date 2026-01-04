package com.spoonofcode.poa.feature.login.domain

import com.spoonofcode.poa.feature.login.domain.model.LoginGoogle

interface LoginGoogleDataSource {
    suspend fun create(googleUserToken: String): Result<LoginGoogle>
}