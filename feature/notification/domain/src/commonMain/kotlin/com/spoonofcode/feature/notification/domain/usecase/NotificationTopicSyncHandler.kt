package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.feature.notification.domain.model.NotificationSettings

interface NotificationTopicSyncHandler {
    suspend fun syncTopics(settings: NotificationSettings): Result<Unit>
}
