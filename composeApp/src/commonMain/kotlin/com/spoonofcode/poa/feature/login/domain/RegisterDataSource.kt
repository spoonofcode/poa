package com.spoonofcode.poa.feature.login.domain

import com.spoonofcode.poa.feature.login.domain.model.Register

interface RegisterDataSource {
    suspend fun create(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result<Register>
}