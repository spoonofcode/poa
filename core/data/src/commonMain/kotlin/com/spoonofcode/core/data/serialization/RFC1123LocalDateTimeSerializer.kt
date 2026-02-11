package com.spoonofcode.core.data.serialization


import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.alternativeParsing
import kotlinx.datetime.format.char
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object RFC1123LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    /**
     * Format for RFC 1123: "Wed, 11 Feb 2026 11:47:40 GMT"
     * Note: We skip the day of week and GMT part for parsing as LocalDateTime
     * doesn't store offset information, and the API seems to provide it in GMT.
     */
    private val format = LocalDateTime.Format {
        // We handle "Wed, " manually or via ignoring if needed,
        // but simple way is to match the structure if possible.
        // For simplicity in KMP, if the format is strictly fixed:
        alternativeParsing({
            // Try parsing with Day of week prefix
            chars("Mon, "); chars("Tue, "); chars("Wed, "); chars("Thu, "); chars("Fri, "); chars("Sat, "); chars("Sun, ")
        }) {
            // Skip if not present or handled differently
        }

        dayOfMonth()
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        year()
        char(' ')
        hour()
        char(':')
        minute()
        char(':')
        second()
        chars(" GMT")
    }

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTimeRFC1123", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val string = decoder.decodeString()
        // If the Day of Week is always there, we can just strip the first 5 chars "Wed, "
        // or use a more robust parsing.
        return try {
            // Simplified parsing: strip "Wed, " and " GMT"
            val stripped = string.substringAfter(", ").substringBefore(" GMT")
            val simpleFormat = LocalDateTime.Format {
                dayOfMonth()
                char(' ')
                monthName(MonthNames.ENGLISH_ABBREVIATED)
                char(' ')
                year()
                char(' ')
                hour()
                char(':')
                minute()
                char(':')
                second()
            }
            LocalDateTime.parse(stripped, simpleFormat)
        } catch (e: Exception) {
            // Fallback to ISO if needed or throw
            LocalDateTime.parse(string)
        }
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        // You might want to implement RFC 1123 formatting here if sending back to server
        encoder.encodeString(value.toString())
    }
}