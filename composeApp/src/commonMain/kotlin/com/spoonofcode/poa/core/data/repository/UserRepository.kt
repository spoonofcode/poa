package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.AddRoleToUserRequest
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.model.UserRequest
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class UserRepository : GenericCrudRepository<UserRequest, User>(
    resourceName = "users",
    requestSerializer = UserRequest.serializer(),
    responseSerializer = User.serializer(),
) {
    suspend fun readAllUsersByRole(roleId: Int): List<User> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users",
                method = HttpMethod.Get,
                queryParams = mapOf("roleId" to roleId.toString())
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(User.serializer()), responseBody)
        }
    }

    suspend fun addRoleToUser(roleId: Int, userId: Int) {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/roles",
                method = HttpMethod.Post,
                customRequestBody = Json.encodeToString(
                    AddRoleToUserRequest.serializer(),
                    AddRoleToUserRequest(roleId = roleId)
                ),
            )
            responseOrException(response).body<String>()
        }
    }
}