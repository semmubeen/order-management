package com.mubeen.multibankgroupassessment

import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        fun getDate(milliSeconds: Long): String? {

            val formatter = SimpleDateFormat("dd-MM-yyyy hh:mm aa")
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
        }
    }

}