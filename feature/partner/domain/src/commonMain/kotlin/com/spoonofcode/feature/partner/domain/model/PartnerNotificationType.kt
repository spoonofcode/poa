package com.spoonofcode.feature.partner.domain.model

enum class PartnerNotificationType {
    GLOBAL,
    USER,
    SERIE,
    PARTNER,
    ;

    fun createTopic(identifier: String): String = "${this.name.lowercase()}_$identifier"

    companion object {
        fun fromString(value: String): PartnerNotificationType? = entries.find { it.name.lowercase().equals(value, ignoreCase = true) }
    }
}
