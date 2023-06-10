package com.skystatus.data.api.model

import com.squareup.moshi.Json

data class CityResponse(
    @field:Json(name = "Key") val key: String,
    @field:Json(name = "Type") val type: String,
    @field:Json(name = "LocalizedName") val localizedName: String,
    @field:Json(name = "Country") val country: CountryResponse?,
    @field:Json(name = "AdministrativeAreaResponse") val administrativeAreaResponse: AdministrativeAreaResponse?,
)

data class CountryResponse(
    @field:Json(name = "ID") val id: String,
    @field:Json(name = "LocalizedName") val localizedName: String,
)

data class AdministrativeAreaResponse(
    @field:Json(name = "ID") val id: String,
    @field:Json(name = "LocalizedName") val localizedName: String,
)