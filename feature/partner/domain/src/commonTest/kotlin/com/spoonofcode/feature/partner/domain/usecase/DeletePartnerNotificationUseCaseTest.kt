package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.partner.domain.di.partnerDomainTestModule
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DeletePartnerNotificationUseCaseTest : BaseTest() {
    private lateinit var deletePartnerNotificationUseCase: DeletePartnerNotificationUseCase
    private lateinit var notificationRepository: PartnerNotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        deletePartnerNotificationUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success when notification is deleted`() =
        runTest {
            val notificationId = "1"
            everySuspend { notificationRepository.deleteNotification(notificationId) } returns Result.success(Unit)

            val result = deletePartnerNotificationUseCase(notificationId)

            assertTrue(result.isSuccess)
            verifySuspend { notificationRepository.deleteNotification(notificationId) }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val notificationId = "1"
            val exception = Exception("Delete failed")
            everySuspend { notificationRepository.deleteNotification(notificationId) } returns Result.failure(exception)

            val result = deletePartnerNotificationUseCase(notificationId)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { notificationRepository.deleteNotification(notificationId) }
        }
}
