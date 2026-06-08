package com.example.espectra.model

import com.google.gson.annotations.SerializedName


data class ApiResponseResponsavel(
    @SerializedName("status") val status: Boolean,
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("items") val items: ResponsavelHome?
)
