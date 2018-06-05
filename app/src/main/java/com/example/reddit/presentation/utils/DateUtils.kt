package com.example.reddit.presentation.utils

import java.util.*


class DateUtils {

    companion object {

        fun getTimeLeftReadable(date: Date): String {
            val currentTime = Calendar.getInstance().timeInMillis

            val diff = currentTime - date.time
            val diffMinutes = diff / (60 * 1000) % 60
            val diffHours = diff / (60 * 60 * 1000)
            val diffInDays = (diff / (1000 * 60 * 60 * 24)).toInt()

            if (diffInDays > 1) {
                return "$diffInDays d"
            } else if (diffHours > 24) {
                return "$diffHours h"
            } else {
                return "$diffMinutes m"
            }
        }

    }
}