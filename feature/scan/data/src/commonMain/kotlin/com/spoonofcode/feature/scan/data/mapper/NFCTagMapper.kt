package com.spoonofcode.feature.scan.data.mapper

import com.spoonofcode.feature.scan.data.remote.model.NFCTagResponse
import com.spoonofcode.feature.scan.domain.model.NFCTag

fun NFCTagResponse.toTag(): NFCTag {
    return NFCTag(
        publicId = tagData.tagType,
        tagType = tagData.publicId,
    )
}