package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.data.model.LoginRequest
import com.spoonofcode.poa.core.model.Login
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

interface LoginRepository {
    suspend fun create(email: String, password: String): Result<Login>
}

class LoginRepositoryImpl() : BaseRepository(
    collectionName = "login",
    sessionTokenRequired = false,
), LoginRepository {
    override suspend fun create(
        email: String,
        password: String
    ): Result<Login> = doRequest(
        method = HttpMethod.Post,
        requestBody = LoginRequest(
            email = email,
            password = password,
        ),
        responseTypeInfo = typeInfo<Login>()
    )
}