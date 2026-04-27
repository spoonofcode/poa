package com.spoonofcode.feature.scan.data

import com.spoonofcode.feature.scan.data.mapper.toTag
import com.spoonofcode.feature.scan.data.remote.RemoteNFCTagDataSource
import com.spoonofcode.feature.scan.domain.model.NFCTag
import com.spoonofcode.feature.scan.domain.repository.NFCTagRepository

class NFCTagRepositoryImpl(
    private val remoteNFCTagDataSource: RemoteNFCTagDataSource,
) : NFCTagRepository {
    override suspend fun getNFCTAg(url: String): Result<NFCTag> =
        remoteNFCTagDataSource.readNFCTagPublicId(url).map { it.toTag() }
}