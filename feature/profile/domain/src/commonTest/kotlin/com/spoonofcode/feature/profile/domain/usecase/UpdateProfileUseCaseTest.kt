package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.profile.domain.di.profileDomainTestModule
import com.spoonofcode.feature.profile.domain.repository.ProfileRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UpdateProfileUseCaseTest : BaseTest() {

    private lateinit var updateProfileUseCase: UpdateProfileUseCase
    private lateinit var profileRepository: ProfileRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(profileDomainTestModule)
        super.beforeTest()
        profileRepository = getKoin().get()
        updateProfileUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success when profileRepository updateUserProfile succeeds`() = runTest {
        everySuspend { profileRepository.updateUserProfile() } returns Result.success(Unit)

        val result = updateProfileUseCase()

        assertTrue(result.isSuccess)
        verifySuspend { profileRepository.updateUserProfile() }
    }

    @Test
    fun `invoke returns failure when profileRepository updateUserProfile throws`() = runTest {
        val exception = Exception("Update profile failed")
        everySuspend { profileRepository.updateUserProfile() } returns Result.failure(exception)

        val result = updateProfileUseCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { profileRepository.updateUserProfile() }
    }
}
