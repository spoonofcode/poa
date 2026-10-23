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
    fun `invoke returns success with NFC tag`() =
        runTest {
            val url = "https://example.com/nfc/123"
            val expectedTag = NFCTag(publicId = "123", tagType = "nft")
            everySuspend { nfcTagRepository.getNFCTAg(url) } returns Result.success(expectedTag)

            val result = getNFCTagUseCase(url)

            assertTrue(result.isSuccess)
            assertEquals(expectedTag, result.getOrNull())
            verifySuspend { nfcTagRepository.getNFCTAg(url) }
        }

    @Test
    fun `invoke returns failure when repository throws`() =
        runTest {
            val url = "invalid_url"
            val exception = Exception("Tag not found")
            everySuspend { nfcTagRepository.getNFCTAg(url) } returns Result.failure(exception)

            val result = getNFCTagUseCase(url)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { nfcTagRepository.getNFCTAg(url) }
        }
}
