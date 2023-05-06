package io.github.seoj17.presentaion.utils

import java.text.SimpleDateFormat
import java.util.Date

object TimeFormatter {
    fun formatTime(target: Int): String {
        return SimpleDateFormat("mm분 ss초").format(target)
    }

    fun formatTime(target: Long): String {
        return SimpleDateFormat("yyyy.MM.dd").format(Date(target))
    }
}
