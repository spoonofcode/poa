package com.spoonofcode.poa.core.domain.partner

import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.network.SessionManager

class GetMyPartnersUseCase(
    private val partnerRepository: PartnerRepository,
    private val sessionManager: SessionManager // Potrzebujemy go do pobrania ID użytkownika
) {
    suspend operator fun invoke(): List<Partner> {
        // Pobieramy ID zalogowanego użytkownika
        val currentUserId = sessionManager.getSessionUserId()
        requireNotNull(currentUserId) { "User must be logged in to see their partners" }

        // Wywołujemy istniejącą w Twoim repozytorium metodę
        return partnerRepository.getMyPartners(userId = currentUserId)
    }
}