package com.skystatus.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val key: String,
    val type: String,
    val localizedName: String,
    val country: Country,
    val administrativeAreaResponse: AdministrativeArea,
): Parcelable
@Parcelize
data class Country(val id: String, val localizedName: String): Parcelable
@Parcelize
data class AdministrativeArea(val id: String, val localizedName: String): Parcelable
