package com.spoonofcode.feature.scan.domain.usecase

import com.spoonofcode.feature.scan.domain.model.Tag
import com.spoonofcode.feature.scan.domain.repository.TagRepository

class GetTagPublicIdUseCase(
    private val tagRepository: TagRepository,
) {
    suspend operator fun invoke(
        url: String
    ): Result<Tag> = tagRepository.getNFCTAg(url = url)
}