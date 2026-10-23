package com.spoonofcode.feature.notification.domain.model

enum class NotificationType {
    GLOBAL,
    USER,
    SERIE,
    PARTNER,
    ;

    fun createTopic(identifier: String): String = "${this.name.lowercase()}_$identifier"

    companion object {
        fun fromString(value: String): NotificationType? = entries.find { it.name.lowercase().equals(value, ignoreCase = true) }
    }
}
