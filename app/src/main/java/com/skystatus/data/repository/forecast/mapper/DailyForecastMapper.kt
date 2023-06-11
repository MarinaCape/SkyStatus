package com.skystatus.data.repository.forecast.mapper

import com.skystatus.data.api.forecast.model.daily.DailyForecastResponse
import com.skystatus.domain.entity.DailyForecast
import com.skystatus.domain.entity.Direction
import com.skystatus.domain.entity.IconWeatherType
import com.skystatus.domain.entity.Sun
import com.skystatus.domain.entity.Temperature
import com.skystatus.domain.entity.UnitQuantity
import com.skystatus.domain.entity.Wind
import com.skystatus.presentation.utils.fullDateToLocalDateTime

fun List<DailyForecastResponse>.toDomain() = map { item ->
    DailyForecast(
        item.date.fullDateToLocalDateTime(),
        IconWeatherType.getIconWeatherByNumber(item.day.icon),
        item.day.iconPhrase,
        item.day.hasPrecipitation,
        Temperature(
            UnitQuantity(
                item.temperature.minimum.value ?: 0.0,
                item.temperature.minimum.unit ?: ""
            ),
            UnitQuantity(item.temperature.maximum.value ?: 0.0, item.temperature.maximum.unit ?: "")
        ),
        Temperature(
            UnitQuantity(
                item.temperature.minimum.value ?: 0.0,
                item.temperature.minimum.unit ?: ""
            ),
            UnitQuantity(item.temperature.maximum.value ?: 0.0, item.temperature.maximum.unit ?: "")
        ),
        Wind(
            Direction(
                item.day.wind.direction.degrees ?: 0,
                item.day.wind.direction.localized ?: ""
            ),
            UnitQuantity(item.day.wind.speed.value ?: 0.0, item.day.wind.speed.unit ?: "")
        ),
        Sun(item.sun.rise, item.sun.set),
        item.day.precipitationProbability,
        item.day.snowProbability,
        item.day.hoursOfPrecipitation,
        item.day.hoursOfRain,
    )
}