package com.example.espectra.service.tentativa

import com.example.espectra.model.tentativa.ResponseTentativa
import com.example.espectra.model.tentativa.Tentativa
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface TentativaService {

    @GET("v1/espectra/tentativa/{id_atividade}")
    suspend fun getTentativasByIdAtividade(

        @Header("x-access-token") token: String,
        @Path(value = "id_atividade") idTentativa: Int,

    ): ResponseTentativa

    @POST("v1/espectra/tentativa/")
    suspend fun postTentativa(

        @Header("x-access-token") token: String,
        @Body request: Tentativa

    ): ResponseTentativa

}