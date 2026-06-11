package com.example.espectra.service

import com.example.espectra.model.ApiResponseHome
import com.example.espectra.model.DataTelaCadastro
import com.example.espectra.model.DataTelaLogin
import com.example.espectra.model.RespostaAutenticacao
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface EspectraApiService {

    // 1. ROTA DE LOGIN
    @Headers("Content-Type: application/json")
    @POST("v1/espectra/usuario/login")
    suspend fun login(
        @Body request: DataTelaLogin
    ): Response<RespostaAutenticacao>

    // 2. ROTA DE CADASTRO
    @Headers("Content-Type: application/json")
    @POST("v1/espectra/usuario")
    suspend fun cadastrar(
        @Body request: DataTelaCadastro
    ): Response<RespostaAutenticacao>

    // 3. ROTA DA HOME (BUSCAR PACIENTES) - CORRIGIDA 🚀
    @GET("v1/espectra/usuario/home/{id}")
    suspend fun buscarPacientes(
        @Header("x-access-token") token: String, // Alterado para bater com o React/Backend
        @Path("id") idUsuario: Int
    ): Response<ApiResponseHome>
}