package com.example.espectra.service.Atividades

import com.example.espectra.model.Atividades.AtividadeResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface AtividadeService {
    @GET("v1/espectra/atividade/{id_paciente}/{id_habilidade}")
    suspend fun getAtividades(

        @Header("x-access-token") token: String,
        @Path(value = "id_paciente") idPaciente: Int,
        @Path(value = "id_habilidade") idHabilidade: Int

    ): AtividadeResponse
}