package com.spoonofcode.feature.tac.domain.usecase

import com.spoonofcode.feature.tac.domain.repository.TACRepository

class UpdateTACSettingsUseCase(
    private val tacRepository: TACRepository,
) {
    suspend operator fun invoke(
        isPrivacyPolicyAccepted: Boolean,
        isTermsAndConditionsAccepted: Boolean,
    ): Result<Unit> =
        tacRepository.setTACSettings(
            isPrivacyPolicyAccepted = isPrivacyPolicyAccepted,
            isTermsAndConditionsAccepted = isTermsAndConditionsAccepted,
        )
}
