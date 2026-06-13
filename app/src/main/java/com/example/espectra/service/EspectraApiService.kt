package com.example.espectra.service

import com.example.espectra.model.ApiResponseHome
import com.example.espectra.model.DataTelaCadastro
import com.example.espectra.model.DataTelaLogin
import com.example.espectra.model.RespostaAutenticacao
import com.example.espectra.model.usuario.ResponseUsuario // Importando o seu model correto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface EspectraApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/espectra/usuario/login")
    suspend fun login(@Body request: DataTelaLogin): Response<RespostaAutenticacao>

    @Headers("Content-Type: application/json")
    @POST("v1/espectra/usuario")
    suspend fun cadastrar(@Body request: DataTelaCadastro): Response<RespostaAutenticacao>

    @GET("v1/espectra/usuario/home/{id}")
    suspend fun buscarPacientes(
        @Header("x-access-token") token: String,
        @Path("id") idUsuario: Int
    ): Response<ApiResponseHome>

    // 🚀 4. NOVA ROTA: Buscar dados do perfil do usuário logado
    @GET("v1/espectra/usuario/{id}")
    suspend fun buscarPerfilUsuario(
        @Header("x-access-token") token: String,
        @Path("id") idUsuario: Int
    ): Response<ResponseUsuario>
}