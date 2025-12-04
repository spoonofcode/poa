package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Login
import com.spoonofcode.poa.core.model.LoginRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

@OpenForMokkery
class LoginRepository() : BaseRepository(
    collectionName = "login",
    sessionTokenRequired = false,
) {
    suspend fun create(
        email: String,
        password: String
    ): Login = doRequest(
        method = HttpMethod.Post,
        requestBody = LoginRequest(
            email = email,
            password = password,
        ),
        responseTypeInfo = typeInfo<Login>()
    )
}