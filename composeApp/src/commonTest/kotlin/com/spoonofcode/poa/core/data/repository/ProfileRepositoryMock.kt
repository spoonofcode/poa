package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.PartnerCategoriesMockData.PARTNER_CATEGORIES
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNERS
import com.spoonofcode.poa.core.data.mockdata.PartnerUserMockData.PARTNER_USERS
import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun profileRepositoryMock() = mock<ProfileRepository> {
    everySuspend { readUserProfile() } returns Result.success(PROFILE_1)
}