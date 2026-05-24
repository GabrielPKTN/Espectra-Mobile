package com.example.espectra.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://10.0.2.2:8080/"

    // ✅ CORRIGIDO: Mudou de EspectraApiService.EspectraApiService para apenas EspectraApiService
    val espectraApiService: EspectraApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EspectraApiService::class.java) // Agora bate perfeitamente com o tipo da variável!
    }
}