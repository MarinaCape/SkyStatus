package com.skystatus.domain.model

import java.time.LocalDateTime

data class HourlyForecast(
    val dateTime: LocalDateTime,
    val weatherIcon: IconWeatherType,
    val iconPhrase: String,
    val hasPrecipitation: Boolean,
    val isDaylight: Boolean,
    val temperature: UnitQuantity,
    val realFeelTemperature:UnitQuantity,
    val wind: Wind,
    val windGust: UnitQuantity,
    val relativeHumidity: Int,
    val precipitationProbability: Int,
    val snowProbability: Int,
    val cloudCover: Int
)

data class UnitQuantity(val value: Double, val unit: String)

data class Wind(
    val direction: Direction,
    val speed: UnitQuantity
)

data class Direction(
    val degrees: Int,
    val localized: String
)