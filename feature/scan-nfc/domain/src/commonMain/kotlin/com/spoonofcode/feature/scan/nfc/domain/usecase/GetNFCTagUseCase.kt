package com.spoonofcode.feature.scan.nfc.domain.usecase

import com.spoonofcode.feature.scan.nfc.domain.model.NFCTag
import com.spoonofcode.feature.scan.nfc.domain.repository.NFCTagRepository

class GetNFCTagUseCase(
    private val nfcTagRepository: NFCTagRepository,
) {
    suspend operator fun invoke(
        url: String?,
    ): Result<NFCTag> {
        if (url == null || !url.contains(POA_DOMAIN_NAME)) {
            return Result.failure(IllegalArgumentException("Invalid NFC tag URL or domain"))
        }
        return nfcTagRepository.getNFCTAg(url = url)
            .mapCatching { tag ->
                if (tag.tagType !in VALID_NFC_TAG_TYPES) {
                    throw IllegalArgumentException("Invalid NFC tag type: ${tag.tagType}")
                }
                tag
            }
    }

    companion object {
        private const val POA_DOMAIN_NAME = "proof-of-wear.com"
        private val VALID_NFC_TAG_TYPES = setOf("nft", "unique_item", "redirect")
    }
}
