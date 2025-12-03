package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Register
import com.spoonofcode.poa.core.model.RegisterRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

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
    ): Register {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                method = HttpMethod.Post,
                requestBody =
                    Json.encodeToString(
                        RegisterRequest.serializer(),
                        RegisterRequest(
                            email = email,
                            password = password,
                            firstName = firstName,
                            lastName = lastName,
                        ),
                    )
            )
            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Register.serializer(), responseBody)
        }
    }
}