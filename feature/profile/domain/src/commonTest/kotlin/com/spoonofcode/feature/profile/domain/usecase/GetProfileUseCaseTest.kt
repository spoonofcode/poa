package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.profile.data.test.ProfileMockData
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

class GetProfileUseCaseTest : BaseTest() {

    private lateinit var getProfileUseCase: GetProfileUseCase
    private lateinit var profileRepository: ProfileRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(profileDomainTestModule)
        super.beforeTest()
        profileRepository = getKoin().get()
        getProfileUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success with profile`() = runTest {
        val result = getProfileUseCase()

        assertTrue(result.isSuccess)
        assertEquals(ProfileMockData.PROFILE_1, result.getOrNull())
        verifySuspend { profileRepository.readUserProfile() }
    }

    @Test
    fun `invoke returns failure when repository throws`() = runTest {
        val exception = Exception("Error reading profile")
        everySuspend { profileRepository.readUserProfile() } returns Result.failure(exception)

        val result = getProfileUseCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { profileRepository.readUserProfile() }
    }
}
