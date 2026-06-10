package com.example.espectra.service.PerfilFamiliar

import com.example.espectra.model.perfilFamiliar.PerfilFamiliar
import com.example.espectra.model.perfilFamiliar.PerfilResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PerfilFamiliarService {
    @GET("v1/espectra/paciente/{id}")
    suspend fun getPerfilById(

        @Header("x-access-token") token: String,
        @Path(value = "id") id: Int

    ): PerfilResponse
}