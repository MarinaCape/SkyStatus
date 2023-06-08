package com.skystatus.domain.entity

import java.time.LocalDateTime

data class DailyForecast(
    val dateTime: LocalDateTime,
    val weatherIcon: IconWeatherType,
    val iconPhrase: String,
    val hasPrecipitation: Boolean,
    val temperature: Temperature,
    val realFeelTemperature:Temperature,
    val wind: Wind,
    val sun: Sun,
    val precipitationProbability: Int,
    val snowProbability: Int,
    val hoursOfPrecipitation: Int,
    val hoursOfRain: Int,
)

data class Sun(
    val rise: String,
    val set: String,
)

data class Temperature(
    val minimum: UnitQuantity,
    val maximum: UnitQuantity,
)