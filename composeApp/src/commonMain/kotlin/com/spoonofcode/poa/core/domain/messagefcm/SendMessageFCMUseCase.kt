package com.spoonofcode.poa.core.domain.messagefcm

import com.spoonofcode.poa.core.data.repository.MessageFCMRepository
import com.spoonofcode.poa.core.model.MessageFCM

class SendMessageFCMUseCase(
    private val messageFCMRepository: MessageFCMRepository,
) {
    suspend operator fun invoke(
        messageDto: MessageFCM,
    ) {
        messageFCMRepository.sendMessage(messageDto)
    }
}