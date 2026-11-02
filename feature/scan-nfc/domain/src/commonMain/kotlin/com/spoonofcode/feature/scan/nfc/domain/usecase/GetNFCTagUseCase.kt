package com.spoonofcode.feature.scan.nfc.domain.usecase

import com.spoonofcode.core.network.NetworkConfig
import com.spoonofcode.feature.scan.nfc.domain.model.NFCTag
import com.spoonofcode.feature.scan.nfc.domain.repository.NFCTagRepository

class GetNFCTagUseCase(
    private val nfcTagRepository: NFCTagRepository,
    private val networkConfig: NetworkConfig,
) {
    suspend operator fun invoke(
        url: String?,
    ): Result<NFCTag> {
        if (url == null || !url.contains(POA_DOMAIN_NAME)) {
            return Result.failure(IllegalArgumentException("Invalid NFC tag URL or domain"))
        }
        val transformedUrl = transformUrl(url, networkConfig.baseUrl)
        return nfcTagRepository.getNFCTAg(url = transformedUrl)
            .mapCatching { tag ->
                if (tag.tagType !in VALID_NFC_TAG_TYPES) {
                    throw IllegalArgumentException("Invalid NFC tag type: ${tag.tagType}")
                }
                tag
            }
    }

    companion object {
        private const val POA_DOMAIN_NAME = "proof-of-wear.com"
        private const val POA_AUTH_DOMAIN_NAME = "auth.proof-of-wear.com"
        private val VALID_NFC_TAG_TYPES = setOf("nft", "unique_item", "redirect")

        fun transformUrl(originalUrl: String, baseUrl: String): String {
            val targetHost =
                baseUrl
                    .removePrefix("https://")
                    .removePrefix("http://")
                    .substringBefore("/")

            return if (originalUrl.contains(POA_AUTH_DOMAIN_NAME)) {
                originalUrl.replace(POA_AUTH_DOMAIN_NAME, targetHost)
            } else {
                originalUrl.replace(POA_DOMAIN_NAME, targetHost)
            }
        }
    }
}
