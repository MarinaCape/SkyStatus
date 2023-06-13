package com.skystatus.domain.entity

import com.skystatus.R

enum class SunsetType(val color: Int, val title: Int, val description: Int) {
    POOR(R.drawable.sp_circle_red, R.string.dawn_title_poor, R.string.dawn_description_poor),
    FAIR(R.drawable.sp_circle_orange, R.string.dawn_title_fair, R.string.dawn_description_fair),
    GOOD(R.drawable.sp_circle_yellow, R.string.dawn_title_good, R.string.dawn_description_good),
    GREAT(R.drawable.sp_circle_green, R.string.dawn_title_great, R.string.dawn_description_great);

    companion object {
        fun getType(percentage: Double) =
            when (percentage) {
                in 0.0..25.0 -> POOR
                in 25.1..50.0 -> FAIR
                in 50.1..75.0 -> GOOD
                in 75.1..100.0 -> GREAT
                else -> POOR
            }
    }
}

