package com.spoonofcode.feature.scan.data.mapper

import com.spoonofcode.feature.scan.data.remote.model.TagResponse
import com.spoonofcode.feature.scan.domain.model.Tag

fun TagResponse.toTag(): Tag {
    return Tag(
        publicId = tagData.tagType,
        tagType = tagData.publicId,
    )
}