package com.spoonofcode.feature.notification.domain.model

enum class NotificationType {
    global,
    user,
    serie,
    partner;

    fun createTopic(identifier: String): String {
        return "${this.name}_$identifier"
    }

    companion object {
        fun fromString(value: String): NotificationType? {
            return entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}