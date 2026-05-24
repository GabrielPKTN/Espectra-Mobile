package com.example.espectra.service

import com.example.espectra.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EspectraApiService {
    @POST("auth/login") // Ajuste a rota para a correspondente do seu backend
    suspend fun login(@Body request: DataTelaLogin): Response<RespostaAutenticacao>

    @POST("auth/cadastro") // Ajuste a rota para a correspondente do seu backend
    suspend fun cadastrar(@Body request: DataTelaCadastro): Response<RespostaAutenticacao>
}