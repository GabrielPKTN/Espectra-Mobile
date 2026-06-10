package com.example.espectra.service

import com.example.espectra.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Headers
import retrofit2.http.Path


interface EspectraApiService {


    @Headers("Content-Type: application/json")


    @POST("v1/espectra/usuario/login")
    suspend fun login(
        @Body request: DataTelaLogin
    ): Response<RespostaAutenticacao>

    @Headers("Content-Type: application/json")
    @POST("v1/espectra/usuario")
    suspend fun cadastrar(
        @Body request: DataTelaCadastro
    ): Response<RespostaAutenticacao>


    @GET("usuarios/{idUsuario}/pacientes")
    suspend fun buscarPacientes(
        @Header("Authorization") token: String,
        @Path("idUsuario") idUsuario: Int
    ): Response<PacientesResponse>
}