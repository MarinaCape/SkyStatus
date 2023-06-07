package com.skystatus.domain.entity

data class City(
    val key: String,
    val type: String,
    val localizedName: String,
    val country: Country,
    val administrativeAreaResponse: AdministrativeArea,
)

data class Country(val id: String, val localizedName: String)

data class AdministrativeArea(val id: String, val localizedName: String)
