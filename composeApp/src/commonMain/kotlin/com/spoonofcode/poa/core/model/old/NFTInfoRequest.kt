package com.spoonofcode.poa.core.model.old

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NFTInfoRequest(
    val title: String,
    val description: String,
    val minNumberOfPeople: Int,
    val maxNumberOfPeople: Int,
    val cost: Int,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val clubId: Int,
    val roomId: Int,
    val typeId: Int,
    val levelId: Int,
    val creatorUserId: Int,
)