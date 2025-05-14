package com.spoonofcode.poa.core.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NFTInfo(
    val tagId: String,
    val publicId: String,
    val series: String,
    val timestamp: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val readCtr: String,
    val owner: String,
    val ownerEmail: String,
    val contractAddress: String,
    val tokenId: String,
    val secret: String,
    val nftImage: String,
    val nftAnimation: String,
    val nftName: String,
    val blockchain: String,
    val link: String,
    val linkText: String,
)