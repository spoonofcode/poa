package com.spoonofcode.core.domain.ext

private val EMAIL_REGEX =
    Regex(
        pattern = "^[A-Za-z0-9.!#\$%&'*+/=?^_`{|}~-]+@[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)+$",
    )

fun String.isValidEmail(): Boolean {
    val trimmed = this.trim()
    return trimmed.isNotEmpty() && trimmed.length <= 254 && EMAIL_REGEX.matches(trimmed)
}

fun String.isValidHttpsUrl(): Boolean = this.trim().startsWith("https")
