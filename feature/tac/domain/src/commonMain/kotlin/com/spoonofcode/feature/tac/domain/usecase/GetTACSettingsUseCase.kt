package com.spoonofcode.feature.tac.domain.usecase

import com.spoonofcode.feature.tac.domain.model.TACSettings
import com.spoonofcode.feature.tac.domain.repository.TACRepository

class GetTACSettingsUseCase(
    private val tacRepository: TACRepository,
) {
    suspend operator fun invoke(): Result<TACSettings> = runCatching {
        tacRepository.getTACSettings()
    }
}