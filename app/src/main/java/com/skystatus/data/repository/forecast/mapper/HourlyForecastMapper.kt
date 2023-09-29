package com.skystatus.data.repository.forecast.mapper

import com.skystatus.data.api.forecast.model.hourly.HourlyForecastResponse
import com.skystatus.domain.model.Direction
import com.skystatus.domain.model.HourlyForecast
import com.skystatus.domain.model.IconWeatherType
import com.skystatus.domain.model.UnitQuantity
import com.skystatus.domain.model.Wind
import com.skystatus.presentation.utils.fullDateToLocalDateTime

fun List<HourlyForecastResponse>.toDomain() = map { item ->
    HourlyForecast(
        item.DateTime.fullDateToLocalDateTime(),
        IconWeatherType.getIconWeatherByNumber(item.WeatherIcon),
        item.IconPhrase,
        item.HasPrecipitation,
        item.IsDaylight,
        UnitQuantity(item.Temperature?.Value?:0.0, item.Temperature?.Unit?:""),
        UnitQuantity(item.RealFeelTemperature?.Value?:0.0, item.RealFeelTemperature?.Unit?:""),
        Wind(
            Direction(item.Wind?.Direction?.Degrees?:0, item.Wind?.Direction?.Localized?:""),
            UnitQuantity(item.Wind?.Speed?.Value?:0.0, item.Wind?.Speed?.Unit?:"")
        ),
        UnitQuantity(item.WindGust?.Speed?.Value?:0.0, item.WindGust?.Speed?.Unit?:""),
        item.RelativeHumidity,
        item.PrecipitationProbability,
        item.SnowProbability,
        item.CloudCover
    )

}