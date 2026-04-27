package com.spoonofcode.feature.scan.data

import com.spoonofcode.feature.scan.data.mapper.toTag
import com.spoonofcode.feature.scan.data.remote.RemoteTagDataSource
import com.spoonofcode.feature.scan.domain.model.Tag
import com.spoonofcode.feature.scan.domain.repository.TagRepository

class TagRepositoryImpl(
    private val remoteTagDataSource: RemoteTagDataSource,
) : TagRepository {
    override suspend fun getNFCTAg(url: String): Result<Tag> =
        remoteTagDataSource.readTagPublicId(url).map { it.toTag() }
}