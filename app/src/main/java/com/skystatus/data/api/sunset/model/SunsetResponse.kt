package com.skystatus.data.api.sunset.model

data class SunsetResponse(
    val features: List<FeatureResponse>,
    val type: String
)


data class FeatureResponse(
    val geometry: GeometryResponse,
    val properties: PropertiesResponse,
    val type: String
)

data class GeometryResponse(
    val coordinates: List<Double>,
    val type: String
)

data class DawnResponse(
    val astronomical: String,
    val civil: String,
    val nautical: String
)

data class PropertiesResponse(
    val dawn: DawnResponse,
    val distance: Double,
    val imported_at: String,
    val last_updated: String,
    val quality: String,
    val quality_percent: Double,
    val quality_value: Double,
    val source: String,
    val temperature: Double,
    val type: String,
    val valid_at: String
)