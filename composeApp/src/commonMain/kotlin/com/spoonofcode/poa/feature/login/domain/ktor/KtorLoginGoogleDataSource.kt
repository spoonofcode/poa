package com.spoonofcode.poa.feature.login.domain.ktor

import com.spoonofcode.poa.feature.login.domain.ktor.model.KtorLoginGoogle


interface KtorLoginGoogleDataSource {
    suspend fun create(googleUserToken: String): Result<KtorLoginGoogle>
}