package io.github.seoj17.canyongg.utils

import java.text.DecimalFormat

object NumberFormatter {
    fun formatNumber(number: Number): String {
        return DecimalFormat("###,###").format(number)
    }
}
