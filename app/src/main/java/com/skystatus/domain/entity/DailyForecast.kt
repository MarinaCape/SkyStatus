package com.skystatus.domain.entity

import com.skystatus.data.api.forecast.model.daily.WindGustResponse
import java.time.LocalDateTime

data class DailyForecast(
    val dateTime: LocalDateTime,
    val weatherIcon: IconWeatherType,
    val iconPhrase: String,
    val hasPrecipitation: Boolean,
    val temperature: Temperature,
    val realFeelTemperature:Temperature,
    val wind: Wind,
    val windGut: UnitQuantity,
    val sun: Sun,
    val precipitationProbability: Int,
    val thunderstormProbability: Int,
    val snowProbability: Int,
    val hoursOfPrecipitation: Int,
    val hoursOfRain: Int,
    val cloudCover: Int,
    val evapotranspiration: UnitQuantity,
    val rain: UnitQuantity,
    val relativeHumidity: Int
)

data class Sun(
    val rise: LocalDateTime,
    val set: LocalDateTime,
)

data class Temperature(
    val minimum: UnitQuantity,
    val maximum: UnitQuantity,
)