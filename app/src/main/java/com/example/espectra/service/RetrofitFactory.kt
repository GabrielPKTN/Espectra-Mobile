package com.example.espectra.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    val BASE_URL = "https://minha-api-unica.azurewebsites.net"

    private val retrofitFactory = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getEspectraService(): EspectraService {

        return retrofitFactory.create(EspectraService::class.java)

    }

}