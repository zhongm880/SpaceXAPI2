package com.example.spacexapi.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Constants {

    companion object {

        const val URL = "https://api.spacexdata.com/"
        const val LAUNCHES_END_POINT = "v3/launches"
        const val INFO_END_POINT = "v3/info"
        const val QUERY_LAUNCHES_YEAR = "launch_year"

        private val inputParseFormatter = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private val outputParseFormatter = "mm/dd/yyyy 'at' hh:mm"

        fun getTodayFromDays(time: Long): String {
            val today = System.currentTimeMillis()

            return (today - time).toString()
        }

        fun parseUTCDate(input: String): String {
            //2006-03-24T22:30:00.000Z
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    val inputFormat = DateTimeFormatter.ofPattern(inputParseFormatter)
                    val outputFormat = DateTimeFormatter.ofPattern(outputParseFormatter)
                    outputFormat.format(LocalDateTime.parse(input, inputFormat))
                } else {
                    val inputFormat = SimpleDateFormat(inputParseFormatter)
                    val outPutFormat = SimpleDateFormat(outputParseFormatter)
                    outPutFormat.format(inputFormat.parse(input))
                }
        }
    }
}