package com.lelestacia.valorantgamepedia.utility

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateFormatter {

    fun format(currentDate : String, targetTimeZone: String): String {
        val instant = Instant.parse(currentDate)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy | HH:mm")
            .withZone(ZoneId.of(targetTimeZone))
        return formatter.format(instant)
    }
}