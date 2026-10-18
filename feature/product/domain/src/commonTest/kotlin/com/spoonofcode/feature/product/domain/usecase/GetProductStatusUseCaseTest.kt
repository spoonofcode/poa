package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.product.domain.di.productDomainTestModule
import com.spoonofcode.feature.product.domain.model.ProductStatus
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetProductStatusUseCaseTest : BaseTest() {

    private lateinit var getProductStatusUseCase: GetProductStatusUseCase
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productDomainTestModule)
        super.beforeTest()
        sessionRepository = getKoin().get()
        getProductStatusUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns UNASSIGNED when ownerEmail is null`() = runTest {
        val result = getProductStatusUseCase(null)
        assertEquals(ProductStatus.UNASSIGNED, result)
    }

    @Test
    fun `invoke returns UNASSIGNED when ownerEmail is empty`() = runTest {
        val result = getProductStatusUseCase("")
        assertEquals(ProductStatus.UNASSIGNED, result)
    }

    @Test
    fun `invoke returns OWNED_BY_SELF when ownerEmail matches session user email`() = runTest {
        val userEmail = "user@example.com"
        everySuspend { sessionRepository.getSessionUserEmail() } returns userEmail

        val result = getProductStatusUseCase(userEmail)
        assertEquals(ProductStatus.OWNED_BY_SELF, result)
    }

    @Test
    fun `invoke returns OWNED_BY_OTHER when ownerEmail differs from session user email`() = runTest {
        everySuspend { sessionRepository.getSessionUserEmail() } returns "user@example.com"

        val result = getProductStatusUseCase("other@example.com")
        assertEquals(ProductStatus.OWNED_BY_OTHER, result)
    }
}
