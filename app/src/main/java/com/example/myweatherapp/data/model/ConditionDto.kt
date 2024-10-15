package com.example.myweatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ConditionDto(
    @SerializedName("code") val code: Int,
    @SerializedName("icon") val icon: String,
    @SerializedName("text") val text: String
)
