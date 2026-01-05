package com.spoonofcode.poa.feature.login.domain

import com.spoonofcode.poa.feature.login.domain.model.Login

interface LoginCodeDataSource {
    suspend fun create(
        email: String,
        loginCode: String,
    ): Result<Login>
}