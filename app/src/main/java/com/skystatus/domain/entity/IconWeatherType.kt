package com.skystatus.domain.entity

import com.skystatus.R

enum class IconWeatherType(val icon: Int) {
    SUNNY(R.drawable.ic_sunny),
    SNOW(R.drawable.ic_snow),
    SUN_RAINY_CLOUD(R.drawable.ic_rain_cloud),
    MODERATE_RAIN(R.drawable.ic_moderate_rain),
    LIGHT_RAIN(R.drawable.ic_light_rain),
    HEAVY_RAIN(R.drawable.ic_heavy_rain),
    STORMY(R.drawable.ic_stormy),
    CLOUD(R.drawable.ic_cloud),
    PARTIAL_CLOUD(R.drawable.ic_partly_cloud),
    WINDY(R.drawable.ic_windy_weather),
    NIGHT(R.drawable.ic_night),
    NIGHT_CLOUD(R.drawable.ic_night_cloudy),
    NIGHT_RAIN(R.drawable.ic_night_rain);


    companion object {
        fun getIconWeatherByNumber(number: Int) =
            when (number) {
                in 1..3 -> SUNNY
                in 4..6 -> PARTIAL_CLOUD
                in 7..11 -> CLOUD
                12 -> LIGHT_RAIN
                in 13..14 -> SUN_RAINY_CLOUD
                18 -> HEAVY_RAIN
                15,16,17 -> STORMY
                in 19..23 -> CLOUD
                in 24..29 -> SNOW
                32 -> WINDY
                in 33..34 -> NIGHT
                in 34..38 -> NIGHT_CLOUD
                in 39..44 -> NIGHT_RAIN
                else -> SUNNY
            }
    }

}