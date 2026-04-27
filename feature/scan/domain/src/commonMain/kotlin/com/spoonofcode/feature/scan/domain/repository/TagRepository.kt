package com.spoonofcode.feature.scan.domain.repository

interface TagRepository {
    suspend fun getProductPublicId(url: String): Result<String>
}