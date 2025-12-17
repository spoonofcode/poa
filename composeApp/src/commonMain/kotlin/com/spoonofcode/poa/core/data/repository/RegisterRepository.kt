package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.data.model.RegisterRequest
import com.spoonofcode.poa.core.model.Register
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

interface RegisterRepository {
    suspend fun create(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result<Register>
}

class RegisterRepositoryImpl : BaseRepository(
    collectionName = "register",
    sessionTokenRequired = false,
), RegisterRepository {
    override suspend fun create(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result<Register> = doRequest(
        method = HttpMethod.Post,
        requestBody = RegisterRequest(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
        ),
        responseTypeInfo = typeInfo<Register>()
    )
}