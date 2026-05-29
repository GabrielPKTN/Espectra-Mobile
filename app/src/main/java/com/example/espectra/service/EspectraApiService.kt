

package com.example.espectra.service

import com.example.espectra.model.*
import com.example.espectra.model.perfilFamiliar.DataPerfilFamiliar
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface EspectraApiService {
//    @POST("auth/login") // Ajuste a rota para a correspondente do seu backend
//    suspend fun login(@Body request: DataTelaLogin): Response<RespostaAutenticacao>
//
//    @POST("auth/cadastro") // Ajuste a rota para a correspondente do seu backend
//    suspend fun cadastrar(@Body request: DataTelaCadastro): Response<RespostaAutenticacao>

    @GET("paciente/{id}")
    suspend fun buscarPaciente(@Path("id") id: Int): Response<DataPerfilFamiliar>
}