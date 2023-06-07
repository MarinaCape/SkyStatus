package com.skystatus.data.api.model

data class CityResponse(
    val key: String,
    val type: String,
    val localizedName: String,
    val country: CountryResponse,
    val administrativeAreaResponse: AdministrativeAreaResponse,
)

data class CountryResponse(val id: String, val localizedName: String)

data class AdministrativeAreaResponse(val id: String, val localizedName: String)