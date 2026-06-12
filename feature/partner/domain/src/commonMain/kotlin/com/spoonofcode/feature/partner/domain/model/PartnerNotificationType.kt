package com.spoonofcode.feature.partner.domain.model

enum class PartnerNotificationType {
    global,
    user,
    serie,
    partner;

    fun createTopic(identifier: String): String {
        return "${this.name}_$identifier"
    }

    companion object {
        fun fromString(value: String): PartnerNotificationType? {
            return entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}
