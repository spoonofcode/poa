package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.LoginGoogle
import com.spoonofcode.poa.core.model.LoginGoogleRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

@OpenForMokkery
class LoginGoogleRepository : BaseRepository(
//    collectionName = "api/login/google", //TODO #13 Switch to POA backend API
    collectionName = "login/google",
    sessionTokenRequired = false,
) {
    suspend fun create(
        googleUserToken: String,
    ): LoginGoogle {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                method = HttpMethod.Post,
                requestBody =
                    Json.encodeToString(
                        LoginGoogleRequest.serializer(),
                        LoginGoogleRequest(
                            googleUserToken = googleUserToken,
                        ),
                    )
            )
            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(LoginGoogle.serializer(), responseBody)
        }
    }
}