package com.presentation.utils

import java.util.*


class DateUtils {

    companion object {

        fun getTimeLeftReadable(dateInMillis: Long): String {
            val currentTime = Calendar.getInstance().timeInMillis / 1000

            val diff = currentTime - dateInMillis
            val diffMinutes = diff / 60 % 60
            val diffHours = diff / (60 * 60)
            val diffInDays = (diff / (60 * 60 * 24)).toInt()

            return when {
                diffInDays > 1 -> "$diffInDays d"
                diffHours > 1 -> "$diffHours h"
                else -> "$diffMinutes m"
            }
        }

    }
}