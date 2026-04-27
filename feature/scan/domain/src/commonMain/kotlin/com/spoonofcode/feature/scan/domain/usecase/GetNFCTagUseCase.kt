package com.spoonofcode.feature.scan.domain.usecase

import com.spoonofcode.feature.scan.domain.model.NFCTag
import com.spoonofcode.feature.scan.domain.repository.NFCTagRepository

class GetNFCTagUseCase(
    private val NFCTagRepository: NFCTagRepository,
) {
    suspend operator fun invoke(
        url: String
    ): Result<NFCTag> = NFCTagRepository.getNFCTAg(url = url)
}