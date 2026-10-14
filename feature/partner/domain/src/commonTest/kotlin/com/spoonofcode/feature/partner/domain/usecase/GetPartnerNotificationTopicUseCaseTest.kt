package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.partner.domain.di.partnerDomainTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetPartnerNotificationTopicUseCaseTest : BaseTest() {

    private lateinit var getPartnerNotificationTopicUseCase: GetPartnerNotificationTopicUseCase
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        sessionRepository = getKoin().get()
        getPartnerNotificationTopicUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns session user id as topic`() = runTest {
        val userId = "user123"
        everySuspend { sessionRepository.getSessionUserId() } returns userId

        val topic = getPartnerNotificationTopicUseCase()

        assertEquals(userId, topic)
    }
}
