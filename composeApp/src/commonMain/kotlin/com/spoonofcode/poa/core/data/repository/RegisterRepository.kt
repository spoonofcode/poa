package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Register
import com.spoonofcode.poa.core.model.RegisterRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

@OpenForMokkery
class RegisterRepository : BaseRepository(
    collectionName = "register",
    sessionTokenRequired = false,
) {
    suspend fun create(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Register = doRequest(
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