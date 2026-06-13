package com.example.espectra.service.tentativa

import com.example.espectra.model.tentativa.ResponseAtividade
import com.example.espectra.model.tentativa.ResponseAtividades
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface AtividadeService {

    @GET("v1/espectra/atividade")
    suspend fun getAtividadeById(

        @Header("x-access-token") token: String,
        @Query(value = "id_atividade") idAtividade: Int,
        @Query(value = "id_usuario") idUsuario: Int

        ): ResponseAtividade

    @GET("v1/espectra/atividade/{id_paciente}/{id_habilidade}")
    suspend fun getAtividades(

        @Header("x-access-token") token: String,
        @Path(value = "id_paciente") idPaciente: Int,
        @Path(value = "id_habilidade") idHabilidade: Int

    ): ResponseAtividades

}