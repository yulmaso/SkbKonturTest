package com.yulmaso.skbkonturtest.data.web

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class CalendarDeserializer: JsonDeserializer<Calendar> {

    private val calendarFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Calendar {
        val s = json!!.asString
        s.let {
            val calendar = Calendar.getInstance()
            calendar.time = calendarFormat.parse(it)!!
            return calendar
        }
    }
}