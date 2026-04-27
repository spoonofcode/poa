package com.spoonofcode.feature.scan.domain.usecase

import com.spoonofcode.feature.scan.domain.repository.TagRepository

class GetTagPublicIdUseCase(
    private val tagRepository: TagRepository,
) {
    suspend operator fun invoke(
        url: String
    ): Result<String> = tagRepository.getProductPublicId(url = url)
}