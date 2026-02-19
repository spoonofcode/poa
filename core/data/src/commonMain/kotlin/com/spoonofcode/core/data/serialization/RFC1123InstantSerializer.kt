package com.spoonofcode.core.data.serialization

import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.format
import kotlinx.datetime.offsetIn
import kotlinx.datetime.parse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.Instant

object RFC1123InstantSerializer : KSerializer<Instant> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Rfc1123Instant", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Instant =
        Instant.parse(decoder.decodeString(), DateTimeComponents.Formats.RFC_1123)

    override fun serialize(encoder: Encoder, value: Instant) {
        val text = DateTimeComponents.Formats.RFC_1123.format {
            setDateTimeOffset(value, value.offsetIn(TimeZone.UTC))
        }
        encoder.encodeString(text)
    }
}