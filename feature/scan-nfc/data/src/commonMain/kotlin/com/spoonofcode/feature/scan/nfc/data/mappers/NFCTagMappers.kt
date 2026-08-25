package com.spoonofcode.feature.scan.nfc.data.mappers

import com.spoonofcode.feature.scan.nfc.data.model.NFCTagResponse
import com.spoonofcode.feature.scan.nfc.domain.model.NFCTag

fun NFCTagResponse.toTag(): NFCTag {
    return NFCTag(
        publicId = tagData.publicId,
        tagType = tagData.tagType,
    )
}