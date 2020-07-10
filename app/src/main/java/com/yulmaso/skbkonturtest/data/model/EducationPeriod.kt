package com.yulmaso.skbkonturtest.data.model

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class EducationPeriod(
    val start: Calendar,
    val end: Calendar
): Serializable {
    override fun toString(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return "${dateFormat.format(start.time)} - ${dateFormat.format(end.time)}"
    }
}