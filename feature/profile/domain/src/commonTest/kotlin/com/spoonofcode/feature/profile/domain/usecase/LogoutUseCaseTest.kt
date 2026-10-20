package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.profile.domain.di.profileDomainTestModule
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class LogoutUseCaseTest : BaseTest() {

    private lateinit var logoutUseCase: LogoutUseCase
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(profileDomainTestModule)
        super.beforeTest()
        sessionRepository = getKoin().get()
        logoutUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke clears session successfully`() = runTest {
        logoutUseCase()

        verifySuspend { sessionRepository.clearSession() }
    }
}
