package com.skystatus.presentation.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun LocalDate.formatShortDate(): String {
    return DateTimeFormatter.ofPattern("HH", Locale.getDefault()).format(this)
}

fun localDate(dateTime: String): LocalDate {
    return LocalDate.parse(
        dateTime,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    )
}

fun String.fullDateToLocalDateTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val offsetDateTime = OffsetDateTime.parse(this, formatter)
    return LocalDateTime.from(offsetDateTime)
}

fun LocalDateTime.formatLocalDateTime(): String =
    DateTimeFormatter.ofPattern("HH").format(this)

fun LocalDateTime.formatLocalDateTimeHours(): String =
    DateTimeFormatter.ofPattern("HH:mm").format(this)