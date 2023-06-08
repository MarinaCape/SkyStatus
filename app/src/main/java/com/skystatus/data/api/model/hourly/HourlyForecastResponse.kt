package com.skystatus.data.api.model.hourly

data class HourlyForecastResponse(
    val Ceiling: UnitQuantityResponse?,
    val CloudCover: Int,
    val DateTime: String,
    val DewPoint: UnitQuantityResponse?,
    val EpochDateTime: Int,
    val Evapotranspiration: UnitQuantityResponse?,
    val HasPrecipitation: Boolean,
    val Ice: UnitQuantityResponse?,
    val IceProbability: Int,
    val IconPhrase: String,
    val IndoorRelativeHumidity: Int,
    val IsDaylight: Boolean,
    val Link: String,
    val MobileLink: String,
    val PrecipitationProbability: Int,
    val Rain: UnitQuantityResponse,
    val RainProbability: Int,
    val RealFeelTemperature: UnitQuantityResponse?,
    val RealFeelTemperatureShade: UnitQuantityResponse?,
    val RelativeHumidity: Int,
    val Snow: UnitQuantityResponse?,
    val SnowProbability: Int,
    val SolarIrradiance: UnitQuantityResponse?,
    val Temperature: UnitQuantityResponse?,
    val ThunderstormProbability: Int,
    val TotalLiquid: UnitQuantityResponse?,
    val UVIndex: Int,
    val UVIndexText: String,
    val Visibility: UnitQuantityResponse?,
    val WeatherIcon: Int,
    val WetBulbTemperature: UnitQuantityResponse?,
    val Wind: WindResponse?,
    val WindGust: WindGustResponse?
)

data class UnitQuantityResponse(
    val Phrase: String?,
    val Unit: String?,
    val UnitType: Int?,
    val Value: Double?
)

data class WindResponse(
    val Direction: DirectionResponse,
    val Speed: UnitQuantityResponse
)

data class DirectionResponse(
    val Degrees: Int,
    val English: String,
    val Localized: String
)

data class WindGustResponse(
    val Speed: UnitQuantityResponse
)