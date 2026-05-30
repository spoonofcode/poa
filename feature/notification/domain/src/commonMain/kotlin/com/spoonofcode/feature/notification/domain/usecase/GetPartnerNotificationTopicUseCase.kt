package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.feature.notification.domain.model.NotificationType

class GetPartnerNotificationTopicUseCase(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): String = NotificationType.user.createTopic(sessionRepository.getSessionUserId())
}