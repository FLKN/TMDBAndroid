package com.flknlabs.app1

import com.google.gson.annotations.SerializedName

data class SpokenLanguages(
    val english_name: String,
    @SerializedName("iso_639_1") val iso: String,
    val name: String,
)