package com.skystatus.data.repository.mapper

import com.skystatus.data.api.model.CityResponse
import com.skystatus.domain.entity.AdministrativeArea
import com.skystatus.domain.entity.City
import com.skystatus.domain.entity.Country

fun List<CityResponse>.toDomain() = map { item-> City(
    item.key,
    item.type,
    item.localizedName,
    Country(item.country.id, item.country.localizedName),
    AdministrativeArea(
        item.administrativeAreaResponse.id,
        item.administrativeAreaResponse.localizedName
    ),
)}