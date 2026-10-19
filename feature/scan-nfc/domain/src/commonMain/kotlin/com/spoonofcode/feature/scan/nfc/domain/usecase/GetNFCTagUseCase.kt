package com.spoonofcode.feature.scan.nfc.domain.usecase

import com.spoonofcode.feature.scan.nfc.domain.model.NFCTag
import com.spoonofcode.feature.scan.nfc.domain.repository.NFCTagRepository

class GetNFCTagUseCase(
    private val nfcTagRepository: NFCTagRepository,
) {
    suspend operator fun invoke(
        url: String
    ): Result<NFCTag> = nfcTagRepository.getNFCTAg(url = url)
}