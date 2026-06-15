package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType

class GetPartnerNotificationTopicUseCase(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): String = sessionRepository.getSessionUserId()
}
