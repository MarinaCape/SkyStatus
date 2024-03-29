package com.skystatus.domain.model

import java.time.LocalDateTime

data class Sunset(
    val dawnType: DawnType,
    val dawnTime: LocalDateTime,
    val quality_percent: Double,
    val temperature: Double,
    val sunsetInfo: SunsetType
)

