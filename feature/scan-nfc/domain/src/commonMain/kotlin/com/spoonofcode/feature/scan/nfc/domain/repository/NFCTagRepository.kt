package com.spoonofcode.feature.scan.nfc.domain.repository

import com.spoonofcode.feature.scan.nfc.domain.model.NFCTag

interface NFCTagRepository {
    suspend fun getNFCTAg(url: String): Result<NFCTag>
}
