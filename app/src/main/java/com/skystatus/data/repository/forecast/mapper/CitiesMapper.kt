package com.skystatus.data.repository.forecast.mapper

import com.skystatus.data.api.forecast.model.CityResponse
import com.skystatus.domain.model.AdministrativeArea
import com.skystatus.domain.model.City
import com.skystatus.domain.model.Country

fun List<CityResponse>.toDomain() = map { item ->
    City(
        item.key,
        item.type,
        item.localizedName,
        Country(item.country?.id?:"", item.country?.localizedName?:""),
        AdministrativeArea(
            item.administrativeAreaResponse?.id?:"",
            item.administrativeAreaResponse?.localizedName?:""
        ),
    )
}