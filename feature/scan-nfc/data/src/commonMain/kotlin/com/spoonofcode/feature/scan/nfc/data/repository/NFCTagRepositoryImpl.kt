package com.spoonofcode.feature.scan.nfc.data.repository

import com.spoonofcode.feature.scan.nfc.data.mappers.toTag
import com.spoonofcode.feature.scan.nfc.data.remote.RemoteNFCTagDataSource
import com.spoonofcode.feature.scan.nfc.domain.model.NFCTag
import com.spoonofcode.feature.scan.nfc.domain.repository.NFCTagRepository

internal class NFCTagRepositoryImpl(
    private val remoteNFCTagDataSource: RemoteNFCTagDataSource,
) : NFCTagRepository {
    override suspend fun getNFCTAg(url: String): Result<NFCTag> = remoteNFCTagDataSource.readNFCTagPublicId(url).map { it.toTag() }
}
