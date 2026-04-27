package com.spoonofcode.feature.scan.data

import com.spoonofcode.feature.scan.data.remote.RemoteTagDataSource
import com.spoonofcode.feature.scan.domain.repository.TagRepository

class TagRepositoryImpl(
    private val remoteTagDataSource: RemoteTagDataSource,
): TagRepository {
    override suspend fun getProductPublicId(url: String): Result<String> = remoteTagDataSource.readTagPublicId(url)
}