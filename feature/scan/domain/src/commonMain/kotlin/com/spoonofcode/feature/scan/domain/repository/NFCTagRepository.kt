package com.spoonofcode.feature.scan.domain.repository

import com.spoonofcode.feature.scan.domain.model.NFCTag

interface NFCTagRepository {
    suspend fun getNFCTAg(url: String): Result<NFCTag>
}