package com.spoonofcode.feature.scan.nfc.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.scan.nfc.domain.di.scanDomainTestModule
import com.spoonofcode.feature.scan.nfc.domain.model.NFCTag
import com.spoonofcode.feature.scan.nfc.domain.repository.NFCTagRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetNFCTagUseCaseTest : BaseTest() {
    private lateinit var getNFCTagUseCase: GetNFCTagUseCase
    private lateinit var nfcTagRepository: NFCTagRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(scanDomainTestModule)
        super.beforeTest()
        nfcTagRepository = getKoin().get()
        getNFCTagUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success with valid proof-of-wear url and transforms url`() =
        runTest {
            val url = "https://proof-of-wear.com/nft/123"
            val expectedTag = NFCTag(publicId = "123", tagType = "nft")
            everySuspend { nfcTagRepository.getNFCTAg("https://pow-test1-960238188791.europe-west1.run.app/nft/123") } returns Result.success(expectedTag)

            val result = getNFCTagUseCase(url)

            assertTrue(result.isSuccess)
            assertEquals(expectedTag, result.getOrNull())
            verifySuspend { nfcTagRepository.getNFCTAg("https://pow-test1-960238188791.europe-west1.run.app/nft/123") }
        }

    @Test
    fun `invoke returns failure when URL does not contain proof-of-wear domain`() =
        runTest {
            val url = "https://invalid-domain.com/nft/123"

            val result = getNFCTagUseCase(url)

            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is IllegalArgumentException)
        }

    @Test
    fun `invoke returns failure when tag type is invalid`() =
        runTest {
            val url = "https://proof-of-wear.com/nft/123"
            val invalidTag = NFCTag(publicId = "123", tagType = "invalid_type")
            everySuspend { nfcTagRepository.getNFCTAg("https://pow-test1-960238188791.europe-west1.run.app/nft/123") } returns Result.success(invalidTag)

            val result = getNFCTagUseCase(url)

            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is IllegalArgumentException)
        }

    @Test
    fun `transformUrl replaces domain with host from baseUrl`() {
        val originalUrl = "https://proof-of-wear.com/nft/1"
        val originalAuthUrl = "https://auth.proof-of-wear.com/nft/1"

        val devBaseUrl = "https://pow-test1-960238188791.europe-west1.run.app"
        assertEquals(
            "https://pow-test1-960238188791.europe-west1.run.app/nft/1",
            GetNFCTagUseCase.transformUrl(originalUrl, devBaseUrl),
        )
        assertEquals(
            "https://pow-test1-960238188791.europe-west1.run.app/nft/1",
            GetNFCTagUseCase.transformUrl(originalAuthUrl, devBaseUrl),
        )

        val stagingBaseUrl = "https://staging-api.poa.spoonofcode.com"
        assertEquals(
            "https://staging-api.poa.spoonofcode.com/nft/1",
            GetNFCTagUseCase.transformUrl(originalUrl, stagingBaseUrl),
        )

        val prodBaseUrl = "https://api.poa.spoonofcode.com"
        assertEquals(
            "https://api.poa.spoonofcode.com/nft/1",
            GetNFCTagUseCase.transformUrl(originalUrl, prodBaseUrl),
        )
    }
}
