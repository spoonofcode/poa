package com.spoonofcode.feature.tac.domain.usecase

import com.spoonofcode.feature.tac.domain.model.TACSettings
import com.spoonofcode.feature.tac.domain.repository.TACRepository

class UpdateTACSettingsUseCase(
    private val tacRepository: TACRepository,
) {
    suspend operator fun invoke(tacSettings: TACSettings): Result<Unit> {
        tacRepository.setTACSettings(
            isPrivacyPolicyAccepted = tacSettings.isPrivacyPolicyAccepted,
            isTermsAndConditionsAccepted = tacSettings.isTermsAndConditionsAccepted,
        )
        return Result.success(Unit)
    }
}