package com.spoonofcode.feature.scan.nfc.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NFCTagResponse(
    @SerialName("tag_data")
    val tagData: TagData,
) {
    @Serializable
    data class TagData(
        @SerialName("public_id")
        val publicId: String,
        @SerialName("tag_type")
        val tagType: String,
    )
}