package com.spoonofcode.poa.core.domain.user

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.core.ui.utils.FirebasePushService

class SubscribeUserTopicsUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
    private val firebasePushService: FirebasePushService,
) {
    suspend operator fun invoke() {
        subscribeToGlobalTopic()
        subscribeByUserEmail()

        val userProductSeriesIds = userRepository.readUserProductSeriesIds(
            userId = sessionManager.getSessionUserId(),
        )
        userProductSeriesIds.forEach { seriesId ->
            firebasePushService.subscribeToTopic(seriesId)
        }
    }

    private suspend fun subscribeByUserEmail() {
        val userEmail = sessionManager.getSessionUserEmail()
        firebasePushService.subscribeToTopic(userEmail)
    }

    private suspend fun subscribeToGlobalTopic() {
        firebasePushService.subscribeToTopic(GLOBAL_TOPIC)
    }

    companion object {
        private const val GLOBAL_TOPIC = "GLOBAL_TOPIC"
    }
}