package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.profile.domain.di.profileDomainTestModule
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class DeleteAccountUseCaseTest : BaseTest() {

    private lateinit var deleteAccountUseCase: DeleteAccountUseCase
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(profileDomainTestModule)
        super.beforeTest()
        sessionRepository = getKoin().get()
        deleteAccountUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke calls clearSession on sessionRepository`() = runTest {
        deleteAccountUseCase()

        verifySuspend { sessionRepository.clearSession() }
    }
}
