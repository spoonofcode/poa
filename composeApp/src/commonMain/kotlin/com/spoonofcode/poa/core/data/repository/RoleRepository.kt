package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Role
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class RoleRepository : GenericCrudRepository<Role, Role>(
    resourceName = "roles",
    requestSerializer = Role.serializer(),
    responseSerializer = Role.serializer(),
) {
    suspend fun readAllRolesByUserId(userId: Int): List<Role> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "roles",
                method = HttpMethod.Get,
                queryParams = mapOf("userId" to userId.toString())
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Role.serializer()), responseBody)
        }
    }
}