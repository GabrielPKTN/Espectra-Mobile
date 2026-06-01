package com.example.espectra.service


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitFactory {

    val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofitFactory = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getEspectraService(): EspectraService {

        return retrofitFactory.create(EspectraService::class.java)

    }

}