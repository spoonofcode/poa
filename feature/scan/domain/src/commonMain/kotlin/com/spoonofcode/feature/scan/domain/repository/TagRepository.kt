package com.spoonofcode.feature.scan.domain.repository

import com.spoonofcode.feature.scan.domain.model.Tag

interface TagRepository {
    suspend fun getNFCTAg(url: String): Result<Tag>
}