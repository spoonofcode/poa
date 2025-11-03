package com.spoonofcode.poa.core.data.base

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

interface CrudRepository<RQ, RS> : BaseRepository<RQ, RS> {
    suspend fun create(request: RQ): RS
    suspend fun read(id: String): RS
    suspend fun update(id: String, request: RQ): Boolean
    suspend fun delete(id: String): Boolean
    suspend fun readAll(): List<RS>
}

abstract class GenericCrudRepository<RQ : Any, RS : Any>(
    private val resourceName: String,
    private val requestSerializer: KSerializer<RQ>,
    private val responseSerializer: KSerializer<RS>,
    private val sessionTokenRequired: Boolean = true,
) : GenericBaseRepository<RQ, RS>(
    resourceName = resourceName,
    requestSerializer = requestSerializer,
    responseSerializer = responseSerializer,
    sessionTokenRequired = sessionTokenRequired,
), CrudRepository<RQ, RS> {

    override suspend fun create(request: RQ): RS {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/",
                method = HttpMethod.Post,
                sessionTokenRequired = sessionTokenRequired,
                requestBody = request,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(responseSerializer, responseBody)
        }
    }

    override suspend fun read(id: String): RS {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/$id",
                method = HttpMethod.Get,
                sessionTokenRequired = sessionTokenRequired
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(responseSerializer, responseBody)
        }
    }

    override suspend fun update(id: String, request: RQ): Boolean {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/$id",
                method = HttpMethod.Put,
                sessionTokenRequired = sessionTokenRequired,
                requestBody = request
            )

            responseOrException(response).body<String>()
            true
        }
    }

    override suspend fun delete(id: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/$id",
                method = HttpMethod.Delete,
                sessionTokenRequired = sessionTokenRequired
            )

            responseOrException(response).body<String>()
            true
        }
    }

    override suspend fun readAll(): List<RS> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "$resourceName/",
                method = HttpMethod.Get,
                sessionTokenRequired = sessionTokenRequired
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(responseSerializer), responseBody)
        }
    }
}