package com.skystatus.data.repository.sunset.mapper

import com.skystatus.data.api.sunset.model.SunsetResponse
import com.skystatus.domain.entity.DawnType
import com.skystatus.domain.entity.Sunset
import com.skystatus.domain.entity.SunsetType
import com.skystatus.presentation.utils.fullDateToLocalDateTime

fun SunsetResponse.toDomain() =
    Sunset(
        DawnType.getType(features[0].properties.type),
        features[0].properties.dawn.nautical.fullDateToLocalDateTime(),
        features[0].properties.quality_percent,
        features[0].properties.temperature,
        SunsetType.getType(features[0].properties.quality_percent)
    )