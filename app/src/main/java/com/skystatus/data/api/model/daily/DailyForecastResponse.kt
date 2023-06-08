package com.skystatus.data.api.model.daily

import com.squareup.moshi.Json

data class ForecastResponse(@field:Json(name = "DailyForecasts") val dailyForecasts: List<DailyForecastResponse>)

data class DailyForecastResponse(
    @field:Json(name = "Date") val date: String,
    @field:Json(name = "Sun") val sun: SunResponse,
    @field:Json(name = "Temperature") val temperature: TemperatureResponse,
    @field:Json(name = "RealFeelTemperature") val realFeelTemperature: TemperatureResponse,
    @field:Json(name = "RealFeelTemperatureShade") val realFeelTemperatureShade: TemperatureResponse,
    @field:Json(name = "HoursOfSun") val hoursOfSun: Double,
    @field:Json(name = "Day") val day: DayResponse,
)

data class SunResponse(
    @field:Json(name = "Rise") val rise: String,
    @field:Json(name = "Set") val set: String,
)


data class TemperatureResponse(
    @field:Json(name = "Minimum") val minimum: UnitQuantityResponse,
    @field:Json(name = "Maximum") val maximum: UnitQuantityResponse,
)
data class UnitQuantityResponse(
    @field:Json(name = "Phrase") val phrase: String?,
    @field:Json(name = "Unit") val unit: String?,
    @field:Json(name = "UnitType") val unitType: Int?,
    @field:Json(name = "Value") val value: Double?
)

data class DayResponse(
    @field:Json(name = "Icon") val icon: Int,
    @field:Json(name = "IconPhrase") val iconPhrase: String,
    @field:Json(name = "HasPrecipitation") val hasPrecipitation: Boolean,
    @field:Json(name = "PrecipitationType") val precipitationType: String,
    @field:Json(name = "ShortPhrase") val shortPhrase: String,
    @field:Json(name = "LongPhrase") val longPhrase: String,
    @field:Json(name = "PrecipitationProbability") val precipitationProbability: Int,
    @field:Json(name = "ThunderstormProbability") val thunderstormProbability: Int,
    @field:Json(name = "RainProbability") val rainProbability: Int,
    @field:Json(name = "SnowProbability") val snowProbability: Int,
    @field:Json(name = "Wind") val wind: WindResponse,
    @field:Json(name = "Rain") val rain: UnitQuantityResponse,
    @field:Json(name = "HoursOfPrecipitation") val hoursOfPrecipitation: Int,
    @field:Json(name = "HoursOfRain") val hoursOfRain: Int,
)

data class WindResponse(
    @field:Json(name = "Direction") val direction: DirectionResponse,
    @field:Json(name = "Speed") val speed: UnitQuantityResponse
)

data class DirectionResponse(
    @field:Json(name = "Degrees") val degrees: Int,
    @field:Json(name = "English") val english: String,
    @field:Json(name = "Localized") val localized: String
)

data class WindGustResponse(
    @field:Json(name = "Speed")val speed: UnitQuantityResponse
)