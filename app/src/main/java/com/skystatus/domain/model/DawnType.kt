package com.skystatus.domain.model

import com.skystatus.R

enum class DawnType(val type: Int) {
    SUNRISE(R.string.dawn_type_sunrise,),
    SUNSET(R.string.dawn_type_sunset,),
    NONE(0);

    companion object {
        fun getType(type: String) =
            when (type) {
                "Sunrise" -> SUNRISE
                "Sunset" -> SUNSET
                else -> NONE
            }
    }
}

