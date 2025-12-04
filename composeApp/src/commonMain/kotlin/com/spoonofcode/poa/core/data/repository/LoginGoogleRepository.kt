package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.LoginGoogle
import com.spoonofcode.poa.core.model.LoginGoogleRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

@OpenForMokkery
class LoginGoogleRepository : BaseRepository(
//    collectionName = "api/login/google", //TODO #13 Switch to POA backend API
    collectionName = "login/google",
    sessionTokenRequired = false,
) {
    suspend fun create(
        googleUserToken: String,
    ): LoginGoogle = doRequest(
        method = HttpMethod.Post,
        requestBody = LoginGoogleRequest(
            googleUserToken = googleUserToken,
        ),
        responseTypeInfo = typeInfo<LoginGoogle>()
    )
}