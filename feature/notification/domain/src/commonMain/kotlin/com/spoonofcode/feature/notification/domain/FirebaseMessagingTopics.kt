package com.spoonofcode.feature.notification.domain

internal enum class FirebaseMessagingTopicPrefixes {
    global,
    user,
    serie;

    fun createTopic(identifier: String): String {
        return "${this.name}_$identifier"
    }
}