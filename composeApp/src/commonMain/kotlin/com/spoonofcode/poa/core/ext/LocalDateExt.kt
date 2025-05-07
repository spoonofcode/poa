package com.spoonofcode.poa.core.ext

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.char

val DEFAULT_DATE_FORMAT = LocalDate.Format {
    date(
        LocalDate.Format {
            dayOfMonth()
            char('-')
            monthNumber()
            char('-')
            year()
        }
    )
}

fun LocalDate.formatedLocalDate(): String = this.format(DEFAULT_DATE_FORMAT)
