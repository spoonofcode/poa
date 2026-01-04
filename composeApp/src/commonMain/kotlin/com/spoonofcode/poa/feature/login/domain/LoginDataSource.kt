package com.spoonofcode.poa.feature.login.domain

import com.spoonofcode.poa.feature.login.domain.model.Login

interface LoginDataSource {
    suspend fun create(email: String, password: String): Result<Login>
}