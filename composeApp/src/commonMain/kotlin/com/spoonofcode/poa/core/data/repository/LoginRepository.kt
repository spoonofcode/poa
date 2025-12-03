package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Login
import com.spoonofcode.poa.core.model.LoginRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

@OpenForMokkery
class LoginRepository() : BaseRepository(
    collectionName = "login",
    sessionTokenRequired = false,
) {
    suspend fun create(
        email: String,
        password: String
    ): Login {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                method = HttpMethod.Post,
                requestBody =
                    Json.encodeToString(
                        LoginRequest.serializer(),
                        LoginRequest(
                            email = email,
                            password = password,
                        ),
                    )
            )
            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Login.serializer(), responseBody)
        }
    }
}

